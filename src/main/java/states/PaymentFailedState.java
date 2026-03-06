package states;


import entity.Payment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentFailedState implements PaymentState {
    @Override
    public void handle(Payment payment){
        log.info("Payment {} failed", payment.getId());
    }
}
