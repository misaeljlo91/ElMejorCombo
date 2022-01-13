package com.project.elmejorcombo.repositories;

import com.project.elmejorcombo.models.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

}
