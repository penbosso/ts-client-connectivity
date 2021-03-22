package com.example.tsclientconnectivity.viewmodel;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PortfolioRequest {

    @NotNull
    public String portfolioName;
}
