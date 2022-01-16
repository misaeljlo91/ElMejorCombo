package com.project.elmejorcombo.repositories;

import com.project.elmejorcombo.models.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PackRepository extends JpaRepository<Pack, Long> {

}
