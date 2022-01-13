package com.project.elmejorcombo.services;

import com.project.elmejorcombo.models.Shampoo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShampooService {
    Page<Shampoo> findAll(Pageable pageable);
}
