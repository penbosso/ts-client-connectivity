package com.example.tsclientconnectivity.viewmodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

public class ClientRegisterViewModel {

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
}
