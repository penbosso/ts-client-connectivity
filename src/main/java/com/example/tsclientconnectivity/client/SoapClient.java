package com.example.tsclientconnectivity.client;

import com.example.tsclientconnectivity.ordervalidation.Acknowledgement;
import com.example.tsclientconnectivity.ordervalidation.OrderRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class SoapClient {
    @Autowired
    private Jaxb2Marshaller marshaller;

    @Value("${tradegroup2.app.ordervalidation}")
    private String url;

    private WebServiceTemplate template;

    public Acknowledgement submitOrder(OrderRequest request) {
      template = new WebServiceTemplate(marshaller);
      return (Acknowledgement) template.marshalSendAndReceive(String.format("%s/ws",url), request);

    }
}
