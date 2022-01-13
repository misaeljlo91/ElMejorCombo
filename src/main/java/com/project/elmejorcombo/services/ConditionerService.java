package com.project.elmejorcombo.services;

import com.project.elmejorcombo.models.Conditioner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConditionerService {
    Page<Conditioner> findAll(Pageable pageable);
}
