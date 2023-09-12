package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OrderDetails;
import com.example.demo.model.WashPacks;
import com.example.demo.services.WasherService;

@RestController
@RequestMapping("/washers")
public class WasherController {
    @Autowired
    WasherService washerService;

    /** Only the methods that consume rest template are below this comment **/
    //To see the pending orders
    @GetMapping("/findPending")
    public List<OrderDetails> getPendingOrders(){
        return washerService.getPendingOrders();
    }
    @GetMapping("/findCompleted")
    public List<OrderDetails> getCompletedOrders(){
        return washerService.getCompleteOrders();
    }
    
    //The status of the order can be either pending or completed
    @PutMapping("/updateStatus")
    public OrderDetails updateStatusoftheOrder(@RequestBody OrderDetails orderDetails){
        return washerService.updateStatus(orderDetails);
    }
    //To see all the wash packs
    @GetMapping("/seeWashPacks")
    public List<WashPacks> getAllWP(){
        return washerService.getAllWashPacks();
    }
}
