package com.example.tsclientconnectivity.repository;

import com.example.tsclientconnectivity.model.ClientOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientOrderRepository extends CrudRepository<ClientOrder,Long> {

}
