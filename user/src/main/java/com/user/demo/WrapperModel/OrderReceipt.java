package com.user.demo.WrapperModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReceipt {

	    private String orderID;
	    private String CustomerEmail;
	    private String washPackName;
	    private String washPackDetails;
	    private int washPackPrice;


}
