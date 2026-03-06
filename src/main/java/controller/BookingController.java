package controller;


import dto.BookingRequest;
import dto.BulkBookingRequest;
import dto.CancelBookingRequest;
import entity.Booking;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/b2c/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final Logger log = LoggerFactory.getLogger(BookingController.class);

    @PostMapping
    public Booking book(@RequestBody BookingRequest request){
        log.info("Booking request received for show {}", request.getShowId());
        return bookingService.bookSeat(request);
    }

    @PostMapping("/bulk")
    public List<Booking> bulkBook(@RequestBody BulkBookingRequest request){
        log.info("Bulk booking request for {} seats", request.getSeatIds().size());
        return bookingService.bulkBook(request);
    }

    @PostMapping("/cancel")
    public String cancel(@RequestBody CancelBookingRequest request){
        log.info("Cancellation request for {} bookings", request.getBookingIds().size());
        return bookingService.cancelBookings(request);
    }
}