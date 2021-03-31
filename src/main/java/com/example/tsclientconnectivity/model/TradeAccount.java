package com.example.tsclientconnectivity.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity @Data
public class TradeAccount {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;
    @Getter
    @Setter @Column(name="clientId")
    private long clientId;
    private double balance;
    @Column(name="currentBalance")
    private double currentBalance;
    @Column(name="actualBalance")
    private double actualBalance;

}
