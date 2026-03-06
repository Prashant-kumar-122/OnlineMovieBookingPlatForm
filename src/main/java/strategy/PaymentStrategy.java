package strategy;


import dto.PaymentRequest;
import entity.Payment;

public interface PaymentStrategy {
    Payment pay(PaymentRequest request);
}