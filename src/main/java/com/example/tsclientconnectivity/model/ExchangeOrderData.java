package com.example.tsclientconnectivity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "ExchangeOrderData")
@Getter @Setter
public class ExchangeOrderData{
    @Column(name = "stockId")
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    private double price;
    /**
     * this is the long ass order Id string the exchange generates
     */
    @Column(name = "exchangeOrderId")
    private String exchangeOrderId;
    @Column(name = "exchangeUrl")
    private String exchangeUrl;
    private int quantity;
    /**
     * this is our generated order id, which maps to the client order table's primary key
     */
    @Column(name = "orderId")
    private Long orderId;
}