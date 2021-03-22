package com.example.tsclientconnectivity.controller;


import com.example.tsclientconnectivity.client.SoapClient;
import com.example.tsclientconnectivity.ordervalidation.Acknowledgement;
import com.example.tsclientconnectivity.ordervalidation.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private SoapClient client;
    //Authentication auth = SecurityContextHolder.getContext().getAuthentication()

    //create order
    @PostMapping("/submitorder")
    public Acknowledgement invokeSoapClientToSubmitClientOrder(@RequestBody(required = true) OrderRequest request) {
        System.out.println(request.toString());
        return client.submitOrder(request);
    }

    //get details about an order
    //get all orders{open || otherwise}
    //@PostMapping
    //public Respo
    //cancel order
    //update order
}
