package com.project.elmejorcombo.repositories;

import com.project.elmejorcombo.models.Soap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SoapRepository extends JpaRepository<Soap, Long> {

}
