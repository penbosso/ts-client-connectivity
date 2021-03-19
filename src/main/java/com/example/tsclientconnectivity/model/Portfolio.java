package com.example.tsclientconnectivity.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity @Data @NoArgsConstructor
public class Portfolio {
    @Getter   @Setter
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;
    @Getter @Setter
    private String name;


    public Portfolio(String name){
        this.name=name;
    }
}
