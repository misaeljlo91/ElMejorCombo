package com.project.elmejorcombo.services.impl;

import com.project.elmejorcombo.models.Shampoo;
import com.project.elmejorcombo.repositories.ShampooRepository;
import com.project.elmejorcombo.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShampooServiceImpl implements ShampooService {

    @Autowired
    private ShampooRepository shampooRepository;

    @Override
    public Page<Shampoo> findAll(Pageable pageable) { return shampooRepository.findAll(pageable); }
}
