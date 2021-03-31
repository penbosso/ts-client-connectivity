package com.example.tsclientconnectivity.repository;

import com.example.tsclientconnectivity.model.ClientStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientStockRepository extends CrudRepository<ClientStock,Long> {
    @Query
    Iterable<ClientStock> findAllByClientId(@Param("clientId") Long clientId);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM ClientStock u WHERE u.clientId = ?1 and u.ticker = ?2")
    boolean existsByTicker(@Param("clientId")Long clientId,@Param("ticker")String ticker);
}
