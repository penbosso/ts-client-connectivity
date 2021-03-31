package com.example.tsclientconnectivity.service;


import com.example.tsclientconnectivity.model.Client;
import com.example.tsclientconnectivity.reporting.ClientActivity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReportingService {
    public void sendClientActivityToReportingService(String action){
        String reportUrl="http://localhost:3005/client-report";
        RestTemplate restTemplate=new RestTemplate();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Client userDetails = (Client) auth.getPrincipal();
        HttpEntity<ClientActivity> request = new HttpEntity<>(
                new ClientActivity(userDetails.getId(),
                        userDetails.getFname() + " " + userDetails.getLname(),
                        action
                ));

        ResponseEntity<ClientActivity> response = restTemplate
                .exchange(reportUrl, HttpMethod.POST, request, com.example.tsclientconnectivity.reporting.ClientActivity.class);


    }
}
