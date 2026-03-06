package dto;


import enums.PaymentStatus;
import lombok.Data;

@Data
public class PaymentStatusResponse {
    private Long paymentId;
    private PaymentStatus status;
}