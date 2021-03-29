package com.example.tsclientconnectivity.controller;

import com.example.tsclientconnectivity.customResponse.BalanceResponse;
import com.example.tsclientconnectivity.repository.TradeAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/balance")
public class TradeAccountController {
    @Autowired
    TradeAccountRepository tradeAccountRepository;
    
    @GetMapping("/{clientId}")
    public ResponseEntity<Object> getBalance(@PathVariable("clientId")Long clientId){
        if(clientId == 0L) return ResponseEntity.badRequest().build();
        var balance=(tradeAccountRepository.findByClientId(clientId));
        if(balance.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new BalanceResponse(balance.get().getBalance()));

    }


}
