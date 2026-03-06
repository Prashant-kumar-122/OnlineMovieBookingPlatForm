package dto;


import lombok.Data;
import java.util.List;

@Data
public class CancelBookingRequest {
    private Long userId;
    private List<Long> bookingIds;
}