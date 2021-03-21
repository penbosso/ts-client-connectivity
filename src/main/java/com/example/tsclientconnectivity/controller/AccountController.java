package com.example.tsclientconnectivity.controller;

import com.example.tsclientconnectivity.config.jwt.JwtUtility;
import com.example.tsclientconnectivity.model.Client;
import com.example.tsclientconnectivity.model.Portfolio;
import com.example.tsclientconnectivity.repository.ClientRepository;
import com.example.tsclientconnectivity.repository.PortfolioRepository;
import com.example.tsclientconnectivity.viewmodel.ClientLoginRequest;
import com.example.tsclientconnectivity.viewmodel.ClientRegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
//import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
//@CrossOrigin(origins = "*", maxAge = 3600)
public class AccountController {

    ClientRepository clientRepository;

    PortfolioRepository portfolioRepository;

    AuthenticationManager authManager;

    PasswordEncoder encoder;

    JwtUtility jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody ClientLoginRequest viewModel){
       if(viewModel.checkIfNull()) return ResponseEntity.badRequest().body("empty body");

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(viewModel.getEmail(), viewModel.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        Client userDetails = (Client) authentication.getPrincipal();
        var headers=new HttpHeaders();
        headers.set("auth_token",jwt);
        System.out.println(viewModel.getEmail() + " " + viewModel.getPassword());
        return ResponseEntity.ok().headers(headers).build();
    }
    

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody ClientRegisterRequest viewModel){
        if(viewModel == null) return ResponseEntity.badRequest().build();
        if(clientRepository.existsByEmail(viewModel.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        Client client= new Client();
        client.setEmail(viewModel.getEmail());
        client.setFname(viewModel.getFname());
        client.setLname(viewModel.getLname());
        client.setPassword(encoder.encode(viewModel.getPassword()));
        client.setAccountBalance(6000);
        //client.isAdmin()
        //create a default portfolio,and pass the id to the client
        var portfolio= portfolioRepository.save(new Portfolio("Default"));
        client.setPortfolioId(portfolio.getId());

        clientRepository.save(client);
        //ToDo:Log activity with reporting service via post request(param [(clientId, fullName, action=registered, dataTime)])

        return ResponseEntity.ok().body(new MessageResponse("Registration Successful"));
    }

}

class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}