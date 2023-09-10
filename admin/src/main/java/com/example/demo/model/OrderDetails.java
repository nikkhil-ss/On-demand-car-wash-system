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
	@NotEmpty(message = "Washer Name Can't be empty")
	String washerName;
	@NotEmpty(message = "Wash pack can't be empty")
	String washpack;
	@NotEmpty(message = "Date can't be empty")
	long phoneNo;
	@NotEmpty(message = "Pincode can't be empty")
	String areapincode;
	@NotEmpty(message = "status can't be empty")
	String status;
	@NotEmpty(message = "car field can't be empty")
	Car cars;
}
