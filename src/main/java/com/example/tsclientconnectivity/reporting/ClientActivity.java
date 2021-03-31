package com.example.tsclientconnectivity.reporting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ClientActivity {
    private long clientId;
    private String fullName , action;
}
