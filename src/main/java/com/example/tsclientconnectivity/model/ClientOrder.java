package com.example.tsclientconnectivity.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data @Entity
public class ClientOrder {
    @Getter
    @Setter @Column(name = "orderId")
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String orderId;
    @Getter @Setter
    private String side;
    @Getter @Setter
    private String product;
    @Getter @Setter
    private int quantity;
    @Getter @Setter
    private double price;
    @Getter @Setter @Column(name = "clientId")
    private long clientId;
}
