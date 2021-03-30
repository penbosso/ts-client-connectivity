package com.example.tsclientconnectivity.controller;

import com.example.tsclientconnectivity.customResponse.MessageResponse;
import com.example.tsclientconnectivity.config.jwt.JwtUtility;
import com.example.tsclientconnectivity.model.Client;
import com.example.tsclientconnectivity.model.ClientStock;
import com.example.tsclientconnectivity.model.Portfolio;
import com.example.tsclientconnectivity.model.TradeAccount;
import com.example.tsclientconnectivity.reporting.ClientActivity;
import com.example.tsclientconnectivity.repository.ClientRepository;
import com.example.tsclientconnectivity.repository.ClientStockRepository;
import com.example.tsclientconnectivity.repository.PortfolioRepository;
import com.example.tsclientconnectivity.repository.TradeAccountRepository;
import com.example.tsclientconnectivity.viewmodel.ClientLoginRequest;
import com.example.tsclientconnectivity.viewmodel.ClientRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "*", maxAge = 3600)
public class AccountController {


    @Value("${tradegroup2.app.reportServiceUrl}")
    private String reportUrl;
    private final RestTemplate restTemplate=new RestTemplate();
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    TradeAccountRepository tradeAccountRepository;
    @Autowired
    ClientStockRepository clientStockRepository;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
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

        HttpEntity<ClientActivity> request = new HttpEntity<>(
                new ClientActivity(userDetails.getId(),
                        userDetails.getFname() + " " + userDetails.getLname(),
                        "Login"
                ));

        ResponseEntity<ClientActivity> response = restTemplate
                .exchange(reportUrl, HttpMethod.POST, request, ClientActivity.class);
        response.getStatusCodeValue();
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
        client.setPhoneNumber(viewModel.getPhonenumber());
        client.setPassword(encoder.encode(viewModel.getPassword()));

        var dbClient=clientRepository.save(client);
        var portClient=portfolioRepository.save(new Portfolio("Default",dbClient.getId()));
        TradeAccount tradeAccount=new TradeAccount();
        tradeAccount.setBalance(6000);;
        tradeAccount.setClientId(dbClient.getId());
        tradeAccountRepository.save(tradeAccount);
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(viewModel.getEmail(), viewModel.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        Client userDetails = (Client) authentication.getPrincipal();
        /**
         * I need to give some stocks to clients when they sign up
         */
        clientStockRepository.saveAll(ClientStock.createDefaultStock(dbClient.getId(),portClient.getId()));
        var headers=new HttpHeaders();
        headers.set("auth_token",jwt);
        //ToDo:Log activity with reporting service via post request(param [(clientId, fullName, action=registered, dataTime)])
        HttpEntity<ClientActivity> request = new HttpEntity<>(
                new ClientActivity(userDetails.getId(),
                        userDetails.getFname() + " " + userDetails.getLname(),
                        "Register"
                ));

//        ResponseEntity<ClientActivity> response = restTemplate
//                .exchange(reportUrl, HttpMethod.POST, request, ClientActivity.class);
//        response.getStatusCodeValue();
//        ResponseEntity<String> response = restTemplate
//                .exchange("https://jsonplaceholder.typicode.com/todos/1", HttpMethod.GET,null,String.class);
//        System.out.println(response.getBody());
        return ResponseEntity.ok().headers(headers).body(new MessageResponse("Registration Successful"));
    }

}
