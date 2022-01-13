package com.project.elmejorcombo.services;

import com.project.elmejorcombo.models.Soap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SoapService {
    Page<Soap> findAll(Pageable pageable);
}
