package com.project.elmejorcombo.services.impl;

import com.project.elmejorcombo.models.Client;
import com.project.elmejorcombo.repositories.ClientRepository;
import com.project.elmejorcombo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Page<Client> findAll(Pageable pageable){
        return clientRepository.findAll(pageable);
    }
}
