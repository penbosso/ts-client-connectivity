package com.example.tsclientconnectivity.repository;

import com.example.tsclientconnectivity.model.TradeAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TradeAccountRepository extends CrudRepository<TradeAccount,Long> {

    @Query
    Optional<TradeAccount> findByClientId(@Param("clientId")Long clientId);

}
