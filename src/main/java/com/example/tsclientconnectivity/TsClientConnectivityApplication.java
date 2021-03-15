package com.example.tsclientconnectivity;

import com.example.tsclientconnectivity.client.SoapClient;
import com.example.tsclientconnectivity.ordervalidation.Acknowledgement;
import com.example.tsclientconnectivity.ordervalidation.OrderRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TsClientConnectivityApplication {

	@Autowired
	private SoapClient client;

	@PostMapping("/submitorder")
	public Acknowledgement invokeSoapClientToSubmitClientOrder(@RequestBody OrderRequest request) {
		return client.submitOrder(request);
	}
		public static void main(String[] args) {
		SpringApplication.run(TsClientConnectivityApplication.class, args);
	}

}
