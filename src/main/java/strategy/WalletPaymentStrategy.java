package strategy;


import dto.PaymentRequest;
import entity.Payment;
import enums.PaymentStatus;
import enums.PaymentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WalletPaymentStrategy implements PaymentStrategy {
    @Override
    public Payment pay(PaymentRequest request){
        log.info("Processing wallet payment for booking {}", request.getBookingId());
        return Payment.builder()
                .bookingId(request.getBookingId())
                .amount(request.getAmount())
                .type(PaymentType.WALLET)
                .status(PaymentStatus.SUCCESS)
                .build();
    }
}
