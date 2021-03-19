package com.example.tsclientconnectivity.viewmodel;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

public class ClientLoginViewModel {

    @Getter    @Setter //@NotNull
    private String email;
    @Getter @Setter //@NotNull
    private String password;
}
