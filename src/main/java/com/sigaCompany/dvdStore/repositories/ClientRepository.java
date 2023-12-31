package com.sigaCompany.dvdStore.repositories;

import com.sigaCompany.dvdStore.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
    ClientEntity save(ClientEntity clientEntity);
    List<ClientEntity> findAll();
}
