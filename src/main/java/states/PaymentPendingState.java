package states;


import entity.Payment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentPendingState implements PaymentState {
    @Override
    public void handle(Payment payment){
        log.info("Payment {} is pending", payment.getId());
    }
}
