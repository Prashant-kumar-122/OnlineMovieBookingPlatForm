package controller;


import dto.PaymentRequest;
import dto.PaymentStatusResponse;
import entity.Payment;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PaymentService;

@RestController
@RequestMapping("/api/b2c/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @PostMapping
    public PaymentStatusResponse makePayment(@RequestBody PaymentRequest request){
        log.info("Payment request for booking {}", request.getBookingId());
        Payment payment = paymentService.makePayment(request);
        PaymentStatusResponse response = new PaymentStatusResponse();
        response.setPaymentId(payment.getId());
        response.setStatus(payment.getStatus());
        return response;
    }
}