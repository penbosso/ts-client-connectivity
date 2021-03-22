package com.example.tsclientconnectivity.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import javax.persistence.Column;

//This is what the client owns in our database
public class ClientStock {
     @Column(name = "stockId")
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long stockId;
    @Getter   @Setter
    private String ticker;
    @Getter   @Setter
    private double price;
    @Getter   @Setter @Column(name = "stockQuantity")
    private int stockQuantity;
    @Getter   @Setter @Column(name = "clientId")
    private long clientId;
    @Getter   @Setter @Column(name = "portfolioId")
    private long portfolioId;

}
