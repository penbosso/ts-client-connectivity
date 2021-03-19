package com.example.tsclientconnectivity.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Data
//@Table(	name = "clients",
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = "username"),
//                @UniqueConstraint(columnNames = "email")
//        })
@Entity
public class Client {

    @NotNull
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long Id;
    @Getter @Setter
    private String fname;
    @Getter @Setter
    private String lname;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String phonenumber;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private boolean isAdmin;
    @Getter @Setter
    private double accountBalance;
    @Getter @Setter @Column(name = "portfolioId")
    private long portfolioId;


    public Client(long id,String fname,String lname,String email,
                  String phonenumber, String password, boolean isAdmin){
        this.lname=lname;
        this.fname=fname;
        this.password=password;
        this.phonenumber=phonenumber;
        this.isAdmin=isAdmin;
        this.email=email;
        accountBalance=10000;

    }

    public Client(){

    }
}
