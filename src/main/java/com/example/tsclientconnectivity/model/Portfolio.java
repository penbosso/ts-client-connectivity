package com.example.tsclientconnectivity.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity @Data @NoArgsConstructor @Getter @Setter
public class Portfolio {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;
    private String name;
    @Column(name="clientId")
    private long clientId;

    public Portfolio(String name,long clientId){
        this.name=name;
        this.clientId = clientId;
    }
}
