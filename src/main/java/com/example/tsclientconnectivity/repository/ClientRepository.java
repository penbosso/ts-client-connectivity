package com.example.tsclientconnectivity.repository;

import com.example.tsclientconnectivity.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends CrudRepository<Client,Long> {

    @Query
    Client findByEmail(@Param("email")String email);

}
