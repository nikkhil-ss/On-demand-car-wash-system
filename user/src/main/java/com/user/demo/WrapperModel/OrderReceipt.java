package com.user.demo.WrapperModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReceipt {

	    private String orderID;
	    private String customerEmail;
	    private String washerName;
	    private int noOfCarsWashed;
	    private String washPackName;
	    private String washPackDetails;
	    private int washPackPrice;


}
