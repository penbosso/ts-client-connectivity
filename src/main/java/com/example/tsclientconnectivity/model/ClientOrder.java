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
}
//    @Column(name = "orderId")
//    private String orderId;
