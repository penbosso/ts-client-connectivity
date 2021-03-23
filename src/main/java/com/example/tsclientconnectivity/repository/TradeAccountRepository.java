package com.example.tsclientconnectivity.repository;

import com.example.tsclientconnectivity.model.TradeAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeAccountRepository extends CrudRepository<TradeAccount,Long> {


}
