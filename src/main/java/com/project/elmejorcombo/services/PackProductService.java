package com.project.elmejorcombo.services;

import com.project.elmejorcombo.models.PackProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PackProductService {
    Page<PackProduct> findAll(Pageable pageable);
}
