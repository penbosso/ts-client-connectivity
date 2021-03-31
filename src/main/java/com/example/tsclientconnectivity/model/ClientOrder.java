package com.example.tsclientconnectivity.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data @Entity
public class ClientOrder {
    //i don't know what this table is
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "orderId")
    private Long orderId;
    @Getter @Setter
    private String side;
    @Getter @Setter
    private String product;
    @Getter @Setter
    private int quantity;
    @Getter @Setter
    private int strategy;
    @Getter @Setter
    private double price;
    @Getter @Setter @Column(name = "clientId")
    private long clientId;
    @Getter @Setter @Column(name = "exchangeOrderId")
    private String exchangeOrderId;
    @Getter @Setter
    private String status;
    @Getter @Setter
    private String exchange;

    public ClientOrder(String side, String product, int quantity, int strategy, double price, long clientId, String status) {
        this.side = side;
        this.product = product;
        this.quantity = quantity;
        this.strategy = strategy;
        this.price = price;
        this.clientId = clientId;
        this.status = status;
    }
}
//    @Column(name = "orderId")
//    private String orderId;
