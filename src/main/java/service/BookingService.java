package service;



import dto.BookingRequest;
import dto.BulkBookingRequest;
import dto.CancelBookingRequest;
import entity.Booking;
import entity.Seat;
import enums.BookingStatus;
import enums.SeatStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.BookingRepository;
import repository.SeatRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;

    private final Logger log = LoggerFactory.getLogger(BookingService.class);

    // Single booking
    @Transactional
    public Booking bookSeat(BookingRequest request) {
        int retry = 0;
        while (retry < 3) {
            try {
                Seat seat = seatRepository.findById(request.getSeatId())
                        .orElseThrow(() -> new RuntimeException("Seat not found"));

                if (seat.getStatus() == SeatStatus.BOOKED) {
                    log.warn("Seat {} already booked", seat.getSeatNumber());
                    throw new RuntimeException("Seat already booked");
                }

                seat.setStatus(SeatStatus.BOOKED);
                seatRepository.save(seat);

                Booking booking = Booking.builder()
                        .showId(request.getShowId())
                        .userId(request.getUserId())
                        .seatCount(1)
                        .status(BookingStatus.CONFIRMED)
                        .build();

                Booking saved = bookingRepository.save(booking);
                log.info("Booking confirmed: {}", saved.getId());

                return saved;

            } catch (ObjectOptimisticLockingFailureException e) {
                retry++;
                log.warn("Optimistic lock conflict, retrying {}/3", retry);
                if (retry >= 3) throw new RuntimeException("Booking failed after retries");
            }
        }
        throw new RuntimeException("Booking failed");
    }

    // Bulk booking
    @Transactional
    public List<Booking> bulkBook(BulkBookingRequest request) {
        List<Booking> bookings = new ArrayList<>();
        for (Long seatId : request.getSeatIds()) {
            BookingRequest singleRequest = new BookingRequest();
            singleRequest.setUserId(request.getUserId());
            singleRequest.setShowId(request.getShowId());
            singleRequest.setSeatId(seatId);
            try {
                bookings.add(bookSeat(singleRequest));
            } catch (RuntimeException e) {
                log.error("Failed to book seat {}: {}", seatId, e.getMessage());
            }
        }
        return bookings;
    }

    // Cancel single or bulk bookings
    @Transactional
    public String cancelBookings(CancelBookingRequest request) {
        for (Long bookingId : request.getBookingIds()) {
            int retry = 0;
            while (retry < 3) {
                try {
                    Booking booking = bookingRepository.findById(bookingId)
                            .orElseThrow(() -> new RuntimeException("Booking not found: " + bookingId));

                    booking.setStatus(BookingStatus.CANCELLED);
                    bookingRepository.save(booking);

                    // Release seat
                    Seat seat = seatRepository.findById(booking.getShowId())
                            .orElseThrow(() -> new RuntimeException("Seat not found for booking: " + bookingId));
                    seat.setStatus(SeatStatus.AVAILABLE);
                    seatRepository.save(seat);

                    log.info("Booking {} cancelled successfully", bookingId);
                    break;

                } catch (ObjectOptimisticLockingFailureException e) {
                    retry++;
                    log.warn("Optimistic lock conflict during cancellation, retrying {}/3 for booking {}", retry, bookingId);
                    if (retry >= 3) {
                        log.error("Failed to cancel booking {} after retries", bookingId);
                        throw new RuntimeException("Cancellation failed after retries for booking " + bookingId);
                    }
                }
            }
        }
        return "Cancellation completed";
    }
}