package dto;



import lombok.Data;

@Data
public class BookingRequest {
    private Long userId;
    private Long showId;
    private Long seatId;
}