package com.example.demo.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
	@Id
	String orderId;
	@NotEmpty(message = "Email Can't be empty")
	String useremailid;
	@NotEmpty(message = "Wash pack can't be empty")
	String washpack;
	String washerName;
	@NotEmpty(message = "Phone can't be empty")
	long phoneNo;
	Boolean pickUp;
	String pickupAddress;
	
	String status;
	
	Car cars;
}
