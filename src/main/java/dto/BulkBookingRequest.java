package dto;


import lombok.Data;
import java.util.List;

@Data
public class BulkBookingRequest {
    private Long userId;
    private Long showId;
    private List<Long> seatIds;
}
