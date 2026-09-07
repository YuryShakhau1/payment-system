package by.shakhau.fake.bank.controller;


import by.shakhau.fake.bank.controller.dto.PaymentRequest;
import by.shakhau.fake.bank.controller.dto.PaymentStatusResponse;
import by.shakhau.fake.bank.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/fake-bank/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentStatusResponse> createPayment(
            @RequestBody PaymentRequest request) {
        String status = service.create(request.getCard(), request.getPaymentAmount());
        return ResponseEntity.ok(new PaymentStatusResponse(status));
    }
}
