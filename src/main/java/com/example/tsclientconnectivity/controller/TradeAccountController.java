package com.example.tsclientconnectivity.controller;

import com.example.tsclientconnectivity.customResponse.BalanceResponse;
import com.example.tsclientconnectivity.model.TradeAccount;
import com.example.tsclientconnectivity.repository.TradeAccountRepository;
import com.example.tsclientconnectivity.viewmodel.TradeBalanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/balance")
public class TradeAccountController {
    @Autowired
    TradeAccountRepository tradeAccountRepository;

    @GetMapping("/current/{clientId}")
    public ResponseEntity<Object> getCurrentBalance(@PathVariable("clientId")Long clientId){
        if(clientId == 0L) return ResponseEntity.badRequest().build();
        var balance=(tradeAccountRepository.findByClientId(clientId));
        if(balance.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new BalanceResponse(balance.get().getCurrentBalance()));
    }
    @GetMapping("/actual/{clientId}")
    public ResponseEntity<Object> getActualBalance(@PathVariable("clientId")Long clientId){
        if(clientId == 0L) return ResponseEntity.badRequest().build();
        var balance=(tradeAccountRepository.findByClientId(clientId));
        if(balance.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new BalanceResponse(balance.get().getActualBalance()));
    }

    @PutMapping("/actual/{clientId}")
    public ResponseEntity<Object> setActualBalance(@PathVariable("clientId")Long clientId,
                                                   @RequestBody TradeBalanceRequest balanceRequest){
        if(clientId == 0L) return ResponseEntity.badRequest().build();
        var balanceOPtional=(tradeAccountRepository.findByClientId(clientId));
        if(balanceOPtional.isEmpty()) return ResponseEntity.notFound().build();
        TradeAccount balance=balanceOPtional.get();
        if(balanceRequest.getOperation().equals("credit")){
            balance.setActualBalance(balance.getActualBalance() + balanceRequest.getAmount());
        }else if (balanceRequest.getOperation().equals("debit")){
            balance.setActualBalance(balance.getActualBalance()- balanceRequest.getAmount());

        }
        tradeAccountRepository.save(balance);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/current/{clientId}")
    public ResponseEntity<Object> setCurrentBalance(@PathVariable("clientId")Long clientId,
                                                    @RequestBody TradeBalanceRequest balanceRequest){
        if(clientId == 0L) return ResponseEntity.badRequest().build();
        var balanceOPtional=(tradeAccountRepository.findByClientId(clientId));
        if(balanceOPtional.isEmpty()) return ResponseEntity.notFound().build();
        TradeAccount balance=balanceOPtional.get();
        if(balanceRequest.getOperation().equals("credit")){
            balance.setCurrentBalance(balance.getCurrentBalance() + balanceRequest.getAmount());
        }else if (balanceRequest.getOperation().equals("debit")){
            balance.setCurrentBalance(balance.getCurrentBalance()- balanceRequest.getAmount());

        }
        tradeAccountRepository.save(balance);
        return ResponseEntity.ok().build();
    }






}
