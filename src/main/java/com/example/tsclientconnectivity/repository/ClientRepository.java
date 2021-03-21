package com.example.tsclientconnectivity.repository;

import com.example.tsclientconnectivity.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository

public interface ClientRepository extends CrudRepository<Client,Long> {

    @Query
    Optional<Client> findByEmail(@Param("email")String email);


    @Query
    Boolean existsByEmail(@Param("email")String email);

}
