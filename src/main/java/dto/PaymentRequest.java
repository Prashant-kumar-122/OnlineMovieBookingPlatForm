package dto;


import enums.PaymentType;
import lombok.Data;

@Data
public class PaymentRequest {
    private Long bookingId;
    private Double amount;
    private PaymentType type;
}
