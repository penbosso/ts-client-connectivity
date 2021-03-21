package com.example.tsclientconnectivity.viewmodel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClientLoginRequest {

    private String email;
    private String password;

   public boolean checkIfNull(){
       return email.isBlank() || password.isBlank();
   }
}
