package com.example.tsclientconnectivity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

import javax.persistence.Column;

@Entity
@NoArgsConstructor
@Getter @Setter
//This is what the client owns in our database
public class ClientStock {
     @Column(name = "stockId")
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long stockId;
    private String ticker;
    private double price;
      @Column(name = "stockQuantity")
    private int stockQuantity;
      @Column(name = "clientId")
    private long clientId;
      @Column(name = "portfolioId")
    private long portfolioId;

}
