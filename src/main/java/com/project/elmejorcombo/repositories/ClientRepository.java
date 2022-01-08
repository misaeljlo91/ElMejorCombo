package com.project.elmejorcombo.repositories;

import com.project.elmejorcombo.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByUsername(String username);
    Client findByEmail(String email);
}
