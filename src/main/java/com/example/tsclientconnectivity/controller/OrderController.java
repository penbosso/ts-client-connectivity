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

    @PostMapping("/submitorder")
    public Acknowledgement invokeSoapClientToSubmitClientOrder(@RequestBody(required = true) OrderRequest request) {
        System.out.println(request.toString());
        return client.submitOrder(request);
    }
}
