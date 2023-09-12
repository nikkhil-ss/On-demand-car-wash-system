package com.example.demo.services;

import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.OrderDetails;
import com.example.demo.model.WashPacks;


@Service
public class WasherService {

	 	@Autowired
	    private RestTemplate restTemplate;
	 	 String url1="http://ADMIN-SERVICE/admins";
	     //Url to access the methods of Order Service
	     String url2="http://ORDERS-SERVICE/orders";

	     /** Only the methods that use rest template are below this comment **/
	     //To see all the WashPacks
	     public List<WashPacks> getAllWashPacks(){
	         WashPacks[] wp=restTemplate.getForObject(url1+"/findallWP",WashPacks[].class);
	         return (Arrays.asList(wp));
	     }
	     //To see the pending orders
	     public List<OrderDetails> getPendingOrders(){
	         OrderDetails[] pendingList = restTemplate.getForObject(url2+"/findPending",OrderDetails[].class);
	         return Arrays.asList(pendingList);
	     }
	   //To see the completed orders
	     public List<OrderDetails> getCompleteOrders(){
	         OrderDetails[] completedList = restTemplate.getForObject(url2+"/findCompleted",OrderDetails[].class);
	         return Arrays.asList(completedList);
	     }
	     
	     
	     //To update the status of the order by Washer
	     public OrderDetails updateStatus(OrderDetails orderDetails){
	    	 String orderId=orderDetails.getOrderId();
	         HttpHeaders headers = new HttpHeaders();
	         String updateUrl = url2 + "/updateStatus/" + orderId;
	         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	         HttpEntity<OrderDetails> updatedOrder = new HttpEntity<>(orderDetails,headers);
	         OrderDetails od = restTemplate.exchange(updateUrl, HttpMethod.PUT,updatedOrder,OrderDetails.class).getBody();
	         return od;
	     }
	    
	 	
}
