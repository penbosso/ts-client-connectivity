package com.example.tsclientconnectivity.model.reporting;


import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ClientActivity {
    private long clientId;
    private String fullName ,action;

    public ClientActivity(long clientId, String fullName, String action) {
        this.clientId = clientId;
        this.fullName = fullName;
        this.action = action;
    }
}
