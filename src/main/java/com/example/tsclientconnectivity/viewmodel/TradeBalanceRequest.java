package com.example.tsclientconnectivity.viewmodel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TradeBalanceRequest {
    private double amount;
    private String operation; //credit or debit
}
