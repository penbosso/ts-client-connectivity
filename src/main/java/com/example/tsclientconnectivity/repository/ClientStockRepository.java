package com.example.tsclientconnectivity.repository;

import com.example.tsclientconnectivity.model.ClientStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientStockRepository extends CrudRepository<ClientStock,Long> {
}
