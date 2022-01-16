package com.project.elmejorcombo.services.impl;

import com.project.elmejorcombo.models.PackProduct;
import com.project.elmejorcombo.repositories.PackProductRepository;
import com.project.elmejorcombo.services.PackProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PackProductServiceImpl implements PackProductService {

    @Autowired
    private PackProductRepository packProductRepository;

    @Override
    public Page<PackProduct> findAll(Pageable pageable) { return packProductRepository.findAll(pageable); }
}
