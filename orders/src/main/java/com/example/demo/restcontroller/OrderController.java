package com.example.demo.restcontroller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.API_requestException;
import com.example.demo.model.OrderDetails;
import com.example.demo.repository.OrderRepo;

@RestController
@RequestMapping("/orders")

public class OrderController {
	@Autowired
	private OrderRepo orderRepo;

	// To get all orders
	@GetMapping("/findall")
	public List<OrderDetails> findallOrders() {
		return orderRepo.findAll();
	}

	// Find one object by ID
	@GetMapping("/findone/{orderid}")
	public ResponseEntity<OrderDetails> findoneOrder(@PathVariable String orderid) throws API_requestException {
		OrderDetails order = orderRepo.findById(orderid)
				.orElseThrow(() -> new API_requestException("Order with ID -> " + orderid + " not found"));
		return ResponseEntity.ok(order);
	}
	
	private String getUniqueOrderId(long phone) {
		String temp=Long.toString(phone);
		Date currentDate = new Date();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		 String formattedDate = dateFormat.format(currentDate);
		 String generatedOrderId=formattedDate+ temp.substring(5, 8);
		 return generatedOrderId;
		 
		
	}

	// To add an order
	@PostMapping("/add")
	public OrderDetails addOrder(@RequestBody OrderDetails order) {
		String tempOrderId=getUniqueOrderId(order.getPhoneNo());
		// Every Order at conception will be [Pending]
		order.setOrderId(tempOrderId);
		order.setWasherName("NA");
		order.setStatus("Pending");
		if (order.getPickUp() == false) {
			order.setPickupAddress("NA");
		}

		return orderRepo.save(order);
	}

	// To delete specific order with id
	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable String orderId) throws Exception {
		OrderDetails order = orderRepo.findById(orderId)
				.orElseThrow(() -> new Exception("Order with ID -> " + orderId + " not found,deletion failed"));
		orderRepo.delete(order);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Order Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	// To update an order
	@PutMapping("/update/{orderId}")
	public ResponseEntity<OrderDetails> updateOrder(@PathVariable String orderId,
			@RequestBody OrderDetails orderDetails) {
		OrderDetails existingOrder = orderRepo.findById(orderId).orElseThrow(
				() -> new API_requestException("Order with ID -> " + orderId + " not found,update failed"));
		// OrderId won't be updated for @putmapping
		// WasherName can't be updated by user
		existingOrder.setWashpack(orderDetails.getWashpack());
		// Status can't be updated by the user
		existingOrder.setCars(orderDetails.getCars());
		existingOrder.setUseremailid(orderDetails.getUseremailid());
		existingOrder.setPickUp(orderDetails.getPickUp());
		existingOrder.setPickupAddress(orderDetails.getPickupAddress());
		existingOrder.setPhoneNo(orderDetails.getPhoneNo());
		existingOrder.setStatus("Pending");
		existingOrder.setCars(orderDetails.getCars());
		OrderDetails updatedOrder = orderRepo.save(existingOrder);
		return ResponseEntity.ok(updatedOrder);
	}

	/**
	 * Methods that are consumed exclusively by rest templates below this comment
	 */
	// This is called by Admin to update the status of the order(For Completed
	// Order)

	// To filter orders using user emailId
	@GetMapping("/findMyOrders/{useremailid}")
	public List<OrderDetails> getMyOrders(@PathVariable String useremailid) {
		return orderRepo.findAll().stream().filter(order -> order.getUseremailid().contains(useremailid))
				.collect(Collectors.toList());
	}

	// To find all the completed orders
	@GetMapping("/findCompleted")
	public List<OrderDetails> getCompletedOrders() {
		return orderRepo.findAll().stream().filter(order -> order.getStatus().contains("Completed"))
				.collect(Collectors.toList());
	}

	// To find all the pending orders
	@GetMapping("/findPending")
	public List<OrderDetails> getPendingOrders() {
		return orderRepo.findAll().stream().filter(order -> order.getStatus().contains("Pending"))
				.collect(Collectors.toList());
	}

	// To cancel the order
	@PutMapping("/cancelOrder")
	public String cancelOrder(@RequestBody OrderDetails orderDetails) {
		OrderDetails od = orderRepo.findById(orderDetails.getOrderId()).get();
		od.setStatus("Cancelled");
		orderRepo.save(od);
		return "The order with ID -> " + orderDetails.getOrderId() + " is cancelled successfully";
	}

	// To find all the cancelled orders
	@GetMapping("/findCancelled")
	public List<OrderDetails> getCancelledOrders() {
		return orderRepo.findAll().stream().filter(order -> order.getStatus().contains("Cancelled"))
				.collect(Collectors.toList());
	}

	// To update the order as Completed Order
	@PutMapping("/updateStatus/completed/{orderId}")
	public ResponseEntity<OrderDetails> completeOrder(@PathVariable String orderId) {
		OrderDetails existingOrder = orderRepo.findById(orderId).orElseThrow(
				() -> new API_requestException("Order with ID -> " + orderId + " not found,update failed"));
		existingOrder.setStatus("Completed");
		OrderDetails updatedOrder = orderRepo.save(existingOrder);
		return ResponseEntity.ok(updatedOrder);
	}
	//To confirm the order consumed by admin
	@PutMapping("/updateStatus/confirmed/{orderId}")
	public ResponseEntity<?> confirmOrder(@PathVariable String orderId) throws API_requestException {
		OrderDetails existingOrder = orderRepo.findById(orderId).orElseThrow(
				() -> new API_requestException("Order with ID -> " + orderId + " not found,update failed"));
		if(existingOrder.getStatus().equals("Completed") || existingOrder.getStatus().equals("Confirmed(In Progress)") ) {
			return ResponseEntity.ok("Order Already Completed/Confirmed");
//			throw new API_requestException("Order Already Completed/Confirmed");
		}
		else {	
		existingOrder.setStatus("Confirmed(In Progress)");
		OrderDetails updatedOrder = orderRepo.save(existingOrder);
		return ResponseEntity.ok(updatedOrder);
		}
	}
	
	 @PutMapping("/assignWasher")
	    public OrderDetails assignWasher(@RequestBody OrderDetails orderDetails){
	        boolean doesOrderExists=orderRepo.existsById(orderDetails.getOrderId());
	        OrderDetails existingOrder = orderRepo.findById(orderDetails.getOrderId()).orElse(null);
	        if (doesOrderExists && existingOrder.getWasherName().contains("NA")){
	            existingOrder.setWasherName(orderDetails.getWasherName());
	            return orderRepo.save(existingOrder);
	        }
	        else {
	            throw new API_requestException("Order not found in database, washer not assigned");
	        }
	    }

}