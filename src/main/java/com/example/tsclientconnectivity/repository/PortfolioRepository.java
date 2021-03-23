package com.example.tsclientconnectivity.repository;

import com.example.tsclientconnectivity.model.Portfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends CrudRepository<Portfolio,Long> {

}
