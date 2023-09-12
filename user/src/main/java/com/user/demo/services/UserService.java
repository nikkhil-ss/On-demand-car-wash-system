package com.user.demo.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.demo.WrapperModel.OrderReceipt;
import com.user.demo.exceptionHandler.API_ExceptionHandler;
import com.user.demo.model.OrderDetails;
import com.user.demo.model.WashPacks;

@Service
public class UserService {
	@Autowired
	private RestTemplate restTemplate;

	// Admin Service URL
	String url1 = "http://ADMIN-SERVICE/admins";
	// Order Service URL
	String url2 = "http://ORDERS-SERVICE/orders";

	// To see all the WashPacks
	public List<WashPacks> getAllWashPack() {
		WashPacks[] wp = restTemplate.getForObject(url1 + "/findAllWashPack", WashPacks[].class);
		return (Arrays.asList(wp));
	}

	/** Only the methods that use rest template are below this comment **/
	// To add an order from User-end
	public OrderDetails addOrder(OrderDetails orderDetails) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<OrderDetails> addOrderbyUser = new HttpEntity<>(orderDetails,headers);
		return restTemplate.postForObject(url2 + "/add", addOrderbyUser, OrderDetails.class);
	}

	// To update an order from User-end
	// This won't update the status of order
	public OrderDetails updateOrder(OrderDetails orderDetails) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String tempUrl=url2+"/update/"+orderDetails.getOrderId();
		HttpEntity<OrderDetails> updatedOrder = new HttpEntity<>(orderDetails, headers);
		return restTemplate.exchange(tempUrl, HttpMethod.PUT, updatedOrder, OrderDetails.class).getBody();
	}
	
	
	// check it once again
	// To cancel the Order from user end
	public String cancelOrder(OrderDetails orderDetails) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<OrderDetails> cancelledOrder = new HttpEntity<>(orderDetails, headers);
		if(orderDetails.getStatus()=="Pending") {
			ResponseEntity<String> response = restTemplate.exchange(url2 + "/cancelOrder", HttpMethod.PUT, cancelledOrder,
					String.class);
			return response.getBody();
			
		}else {
			return "Order Completed...can not be cancelled";
		}
	}

	// To get the receipt of the order after order is completed
	public OrderReceipt getReceipt(String id) throws Exception {
		OrderDetails od = restTemplate.getForObject(url2 + "/findone/" + id, OrderDetails.class);
		WashPacks wp = restTemplate.getForObject(url1 + "/washPackByName/" + od.getWashpack(), WashPacks.class);
		if (od.getStatus()=="Completed") {
			return new OrderReceipt(id,od.getUseremailid(), wp.getName(), wp.getDescription(), wp.getCost());
		} else {
			throw new API_ExceptionHandler("Your order with ID -> " + id + " is still pending...Reciet genration after completion");
		}
//		return new OrderReceipt(id,"test","testt","test",1233);
		
	}
}