package com.example.tsclientconnectivity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

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

    public ClientStock(String ticker, double price, int stockQuantity, long clientId, long portfolioId) {
        this.ticker = ticker;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.clientId = clientId;
        this.portfolioId = portfolioId;
    }

    public static List<ClientStock> createDefaultStock(long clientId, long portfolioId){
          String[] tics={"IBM","MSFT","TSLA","NFLX","GOOGL","AAPL","ORCL","AMZN"};
          List<ClientStock> dummy=new ArrayList<>();
          for(String s:tics){
             dummy.add(new ClientStock(s,1.05,1000,clientId,portfolioId));
          }
          return dummy;
      }
}
