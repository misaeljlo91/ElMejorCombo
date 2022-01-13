package com.project.elmejorcombo.services.impl;

import com.project.elmejorcombo.models.Conditioner;
import com.project.elmejorcombo.repositories.ConditionerRepository;
import com.project.elmejorcombo.services.ConditionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConditionerServiceImpl implements ConditionerService {

    @Autowired
    private ConditionerRepository conditionerRepository;

    @Override
    public Page<Conditioner> findAll(Pageable pageable) { return conditionerRepository.findAll(pageable); }
}
