package com.example.tsclientconnectivity.repository;


import com.example.tsclientconnectivity.model.ClientTransaction;
import org.springframework.data.repository.CrudRepository;

public interface ClientTransactionRepository  extends CrudRepository<ClientTransaction,Long> {
}
