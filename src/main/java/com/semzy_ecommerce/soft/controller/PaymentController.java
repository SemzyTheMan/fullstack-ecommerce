package com.semzy_ecommerce.soft.controller;

import com.semzy_ecommerce.soft.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/initiate")
    public ResponseEntity<?> initiatePayment(@RequestParam int userId) {
        return transactionService.initiatePayment(userId);
    }

    @PostMapping("/verify/{transactionId}")
    public ResponseEntity<Map<String, Object>> verifyPayment(@PathVariable String transactionId) throws Exception {
        return new ResponseEntity<>(transactionService
                .verifyTransaction(transactionId), HttpStatus.OK);
    }

}
