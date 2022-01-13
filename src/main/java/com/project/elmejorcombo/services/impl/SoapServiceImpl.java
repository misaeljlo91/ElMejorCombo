package com.project.elmejorcombo.services.impl;

import com.project.elmejorcombo.models.Soap;
import com.project.elmejorcombo.repositories.SoapRepository;
import com.project.elmejorcombo.services.SoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SoapServiceImpl implements SoapService {

    @Autowired
    private SoapRepository soapRepository;

    @Override
    public Page<Soap> findAll(Pageable pageable) { return soapRepository.findAll(pageable); }
}
