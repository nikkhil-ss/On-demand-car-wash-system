package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TransactionalDetails;
import com.example.demo.service.PaymentService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/pay")
public class PaymentControlller {
	
	
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/createTransaction/{amount}")
	public TransactionalDetails createTransaction(@PathVariable double amount) {
		return paymentService.createTransaction(amount);
	}

	
}