package com.project.elmejorcombo.services;

import com.project.elmejorcombo.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Page<Client> findAll(Pageable pageable);
}
