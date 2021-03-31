package com.example.tsclientconnectivity.viewmodel;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ClientRegisterRequest {


    private String fname;
    private String lname;
    private String email;
    private String phonenumber;
    private String password;
}
