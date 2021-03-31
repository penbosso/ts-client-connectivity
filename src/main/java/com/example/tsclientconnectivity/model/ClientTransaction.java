package com.example.tsclientconnectivity.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table @Getter @Setter @NoArgsConstructor @Entity @Data
public class ClientTransaction {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    @Column(name = "clientId")
    private Long clientId;
    @Column(name = "orderId")
    private Long orderId;
    private double amount;

    public ClientTransaction(Long clientId, Long orderId) {
        this.clientId = clientId;
        this.orderId = orderId;
    }
}
