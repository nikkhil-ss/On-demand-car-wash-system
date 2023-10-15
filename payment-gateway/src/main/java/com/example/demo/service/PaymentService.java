package com.example.demo.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.demo.model.TransactionalDetails;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {
	private static final String KEY = "rzp_test_Bkrufn6xaXuHvn";
	private static final String KEY_SECRET = "KyYKF60JeA0KWIEwLEPa6el3";
	private static final String CURRENCY = "INR";

	public TransactionalDetails createTransaction(double amount) {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("amount", (amount * 100));
			jsonObject.put("currency", CURRENCY);
			
			
			RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
			Order order = razorpayClient.orders.create(jsonObject);
//			System.out.println(order);
			return prepareTransactionDetails(order);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private TransactionalDetails prepareTransactionDetails(Order order) {
		String orderId = order.get("id");
		String currency = order.get("currency");
		Integer amount = order.get("amount");
		
		TransactionalDetails transactionDetails = new TransactionalDetails(orderId, currency, amount, KEY);
		return transactionDetails;
	}
}
