package com.example.tsclientconnectivity.repository;

import com.example.tsclientconnectivity.model.Portfolio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends CrudRepository<Portfolio,Long> {

    @Query
    Optional<Portfolio> findByClientId(@Param("clientId") Long clientId);

    @Query
    Iterable<Portfolio> findAllByClientId(@Param("clientId") Long clientId);
}
