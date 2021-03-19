package com.example.tsclientconnectivity.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity @Data
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;
    @Getter
    @Setter @Column(name="userId")
    private long userId;
    private double balance;
}
