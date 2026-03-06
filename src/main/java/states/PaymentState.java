package states;


import entity.Payment;

public interface PaymentState {
    void handle(Payment payment);
}