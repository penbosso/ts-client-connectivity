package com.example.tsclientconnectivity.controller;


import com.example.tsclientconnectivity.client.SoapClient;
import com.example.tsclientconnectivity.model.Client;
import com.example.tsclientconnectivity.ordervalidation.Acknowledgement;
import com.example.tsclientconnectivity.ordervalidation.OrderRequest;
import com.example.tsclientconnectivity.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client-order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SoapClient client;
    //private final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    //create order
    @PostMapping()
    public Acknowledgement invokeSoapClientToSubmitClientOrder(@RequestBody(required = true) OrderRequest request) {
       // System.out.println(request.toString());
        // TODO://report to reporting service
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var userId=((Client)auth.getPrincipal()).getId();
        //some logic

        // TODO://report to reportng service
        return ResponseEntity.ok().build();
    }
    //update order
    @PutMapping("/{orderId}")
    public ResponseEntity<Object> updateOrder(@PathVariable(name = "orderId") Long orderId,
                                              @RequestBody OrderRequest request){
        if(!orderRepository.existsById(orderId)) return ResponseEntity.notFound().build();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var userId=((Client)auth.getPrincipal()).getId();

        //some logic
        // TODO://report to reportng service
        return ResponseEntity.ok(request);
    }
}
