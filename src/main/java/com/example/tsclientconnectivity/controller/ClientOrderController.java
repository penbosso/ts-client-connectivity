package com.example.tsclientconnectivity.controller;

import com.example.tsclientconnectivity.client.SoapClient;
import com.example.tsclientconnectivity.ordervalidation.Acknowledgement;
import com.example.tsclientconnectivity.ordervalidation.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client-order")
public class ClientOrderController {
    @Autowired
    private SoapClient client;

    @PostMapping("/submitorder")
    public Acknowledgement invokeSoapClientToSubmitClientOrder(@RequestBody OrderRequest request) {
        return client.submitOrder(request);
    }
    //get details about an order
    //get all orders{open || otherwise}
    //create order
    //cancel order
    //update order
    //Authentication auth = SecurityContextHolder.getContext().getAuthentication()

}
