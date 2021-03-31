package com.example.tsclientconnectivity.repository;

import com.example.tsclientconnectivity.model.ClientOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<ClientOrder,Long> {
    @Query("SELECT u from ClientOrder u WHERE u.clientId = ?1 and u.status = ?2")
    Iterable<ClientOrder> findByStatus(@Param("clientId")Long clientId,@Param("status") String status);

    @Query("SELECT u from ClientOrder u WHERE u.clientId = ?1 and u.status != 'completed' ")
    List<ClientOrder> findByNotCompletedStatus(@Param("clientId")Long clientId);

}
