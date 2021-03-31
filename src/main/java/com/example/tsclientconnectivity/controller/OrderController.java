package com.example.tsclientconnectivity.controller;


import com.example.tsclientconnectivity.client.SoapClient;
import com.example.tsclientconnectivity.model.Client;

import com.example.tsclientconnectivity.model.ClientOrder;
import com.example.tsclientconnectivity.model.ClientStock;
import com.example.tsclientconnectivity.model.ClientTransaction;
import com.example.tsclientconnectivity.ordervalidation.Acknowledgement;
import com.example.tsclientconnectivity.ordervalidation.OrderRequest;
import com.example.tsclientconnectivity.reporting.ClientActivity;

import com.example.tsclientconnectivity.repository.ClientStockRepository;
import com.example.tsclientconnectivity.repository.ClientTransactionRepository;
import com.example.tsclientconnectivity.repository.OrderRepository;
import com.example.tsclientconnectivity.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/client-order")
public class OrderController {

    @Value("${tradegroup2.app.cancelOrderUrl}")
    private String cancelOrderUrl;

    @Value("${tradegroup2.app.privateKey}")
    private String privateKey;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientTransactionRepository expenditureRepository;
    @Autowired
    private ClientStockRepository clientStockRepository;

    @Autowired
    ReportingService reportingService;

    @Autowired
    private SoapClient client;
    private final RestTemplate restTemplate=new RestTemplate();

    //create order
    @PostMapping()
    public Acknowledgement invokeSoapClientToSubmitClientOrder(@RequestBody(required = true) OrderRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var userId=((Client)auth.getPrincipal()).getId();
        orderRepository.save(new ClientOrder(request.getSide(),request.getProduct(),
                    request.getQuantity(),request.getStrategy(),request.getPrice(),userId,"Pending"));
        // TODO://report to reporting service
        reportingService.sendClientActivityToReportingService("Making an order");

        return client.submitOrder(request);
    }

    //get details about an order
    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getOrderDetails(@PathVariable("orderId") Long orderId){
        var data=orderRepository.findById(orderId);
        if(data.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(data.get());
    }
    //get all orders{open || otherwise}
    @GetMapping()
    public ResponseEntity<Object> allOrders(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var userId=((Client)auth.getPrincipal()).getId();
        return ResponseEntity.ok().body(orderRepository.findAll());
    }
    //cancel order
    @DeleteMapping("/{orderId")
    public ResponseEntity<Object> cancelOrder(@PathVariable(name = "orderId")Long orderId){
        if(!orderRepository.existsById(orderId)) return ResponseEntity.badRequest().build();
       // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //var userId=((Client)auth.getPrincipal()).getId();
        //some logic
        HttpEntity<Object> request = new HttpEntity<>("");
        cancelOrderUrl=cancelOrderUrl+orderId;
        ResponseEntity<String> response = restTemplate
                .exchange(cancelOrderUrl, HttpMethod.POST,null,String.class);

        // Done://report to reportng service
        reportingService.sendClientActivityToReportingService(String.format("Cancelling an order with Id= %s",orderId));
        return ResponseEntity.ok().build();
    }

    //update order status
    @PutMapping("/{orderId}")
    public ResponseEntity<Object> updateOrder(@PathVariable(name = "orderId") Long orderId,
                                              @RequestBody OrderRequest request){
        if(!orderRepository.existsById(orderId)) return ResponseEntity.notFound().build();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var userId=((Client)auth.getPrincipal()).getId();
        //some logic
        // TODO://report to reportng service
        reportingService.sendClientActivityToReportingService(String.format("Updating an order with an id= %s",orderId));
        return ResponseEntity.ok(request);
    }
    
    @PutMapping("/market-change")
    public ResponseEntity<?> marketChange(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var userId=((Client)auth.getPrincipal()).getId();

        List<ClientOrder> orders=orderRepository.findByNotCompletedStatus(userId);
        String fullPath="";
        for(ClientOrder order:orders){
            fullPath=order.getExchange() + "/" + privateKey +"/order/"+order.getExchangeOrderId();
            ResponseEntity<String> response = restTemplate
                    .exchange(fullPath, HttpMethod.GET,null,String.class);
            if(response.getStatusCodeValue() == 500 ){

                var ct=new ClientTransaction(order.getClientId(),order.getOrderId());
                if(order.getSide().equalsIgnoreCase("BUY")){
                    ct.setAmount(-1*(order.getPrice()*order.getQuantity()));
                }else if(order.getSide().equalsIgnoreCase("SELL")){
                    ct.setAmount(order.getPrice()*order.getQuantity());
                }
                order.setStatus("Completed");
                orderRepository.save(order);
                var cs=new ClientStock(order.getProduct(),order.getPrice(),order.getQuantity(),userId,1L);
                clientStockRepository.save(cs);
                expenditureRepository.save(ct);
            }
        }
        //Todo: Data for expenditure table
        return ResponseEntity.ok().build();
    }
}
