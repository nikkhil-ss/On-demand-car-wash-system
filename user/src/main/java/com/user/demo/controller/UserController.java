package com.user.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.WrapperModel.OrderReceipt;
import com.user.demo.model.OrderDetails;
import com.user.demo.model.Ratings;
import com.user.demo.model.WashPacks;
import com.user.demo.services.RatingsService;
import com.user.demo.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RatingsService ratingService;

  
    //To add a rating from User-end
    @PostMapping("/addRating")
    public Ratings addRating(@RequestBody Ratings ratings){
        return ratingService.addRating(ratings);
    }
    //For user to see ratings to decide the service
    @GetMapping("/getallRatings")
    public List<Ratings> getallratings(){
        return ratingService.getallRatings();
    }
    //For user to check a washer's history to make informed decision
    @GetMapping("/washerSpecificRating/{washerName}")
    public List<Ratings> washerSpecificRating(@PathVariable String washerName){
        return ratingService.washerSpecific(washerName);
    }

	// To see all the wash packs
	@GetMapping("/getAllWashPacks")
	public List<WashPacks> getAllWashPacks() {
		return userService.getAllWashPack();
	}

	/**
	 * Only the methods that call rest-template methods from services are below this
	 * comment
	 **/
	// To add an order from User-end
	@PostMapping("/addOrder")
	public OrderDetails addOrder(@RequestBody OrderDetails orderDetails) {
		return userService.addOrder(orderDetails);
	}

	// To update and order from User-end
	// This won't update the status of order
	@PutMapping("/updateOrder")
	public OrderDetails updateOrder(@RequestBody OrderDetails orderDetails) {
		return userService.updateOrder(orderDetails);
	}

	// To cancel the Order from user end
	@PutMapping("/cancelOrder")
	public String cancelOrder(@RequestBody OrderDetails orderDetails) {
		return userService.cancelOrder(orderDetails);
	}

	// To get the receipt of the order after order is completed
	@GetMapping("/getReceipt/{id}")
	public OrderReceipt getReceipt(@PathVariable String id) throws Exception {
		return userService.getReceipt(id);
	}
}