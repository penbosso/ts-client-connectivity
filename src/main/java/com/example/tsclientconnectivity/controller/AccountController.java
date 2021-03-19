package com.example.tsclientconnectivity.controller;

import com.example.tsclientconnectivity.model.Client;
import com.example.tsclientconnectivity.model.Portfolio;
import com.example.tsclientconnectivity.repository.ClientRepository;
import com.example.tsclientconnectivity.repository.PortfolioRepository;
import com.example.tsclientconnectivity.viewmodel.ClientLoginViewModel;
import com.example.tsclientconnectivity.viewmodel.ClientRegisterViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AccountController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PortfolioRepository portfolioRepository;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody ClientLoginViewModel viewModel){
       // if(viewModel == null) return ResponseEntity.badRequest().body("empty body");
        
        System.out.println(viewModel.getEmail() + " " + viewModel.getPassword());
        return ResponseEntity.ok().build();
    }
    

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody ClientRegisterViewModel viewModel){
        if(viewModel == null) return ResponseEntity.badRequest().build();
        Client client= new Client();
        client.setEmail(viewModel.getEmail());
        client.setFname(viewModel.getFname());
        client.setLname(viewModel.getLname());
        client.setPassword(viewModel.getPassword());
        client.setAccountBalance(6000);
        //client.isAdmin()
        //create a default portfolio,and pass the id to the client
        var portfolio= portfolioRepository.save(new Portfolio("Default"));
        client.setPortfolioId(portfolio.getId());

        clientRepository.save(client);
        //ToDo:Log activity with reporting service via post request(param [(clientId, fullName, action=registered, dataTime)])
        System.out.println(viewModel.getEmail() + " " + viewModel.getPassword());
        var headers=new HttpHeaders();
        headers.set("access_token","eyndsjbfdfjd1651320");
        return ResponseEntity.ok().headers(headers).build();
    }

}
