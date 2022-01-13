package com.project.elmejorcombo.controllers;

import com.project.elmejorcombo.dtos.ConditionerDTO;
import com.project.elmejorcombo.models.Conditioner;
import com.project.elmejorcombo.repositories.ConditionerRepository;
import com.project.elmejorcombo.requestBodies.ProductsDataADMIN;
import com.project.elmejorcombo.services.ConditionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConditionerController {

    @Autowired
    private ConditionerService conditionerService;

    @Autowired
    private ConditionerRepository conditionerRepository;

    //API Rest ADMIN
    @GetMapping("/admin/conditioners") //Get list conditioners ADMIN only
    public Page<Conditioner> getAllShampoos(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<Conditioner> listConditioners = conditionerService.findAll(pageable);
        return listConditioners;
    }

    @GetMapping("/admin/conditioners/{id}") //Get conditioner by ID ADMIN only
    public ConditionerDTO getConditionerID(@PathVariable Long id){
        return conditionerRepository.findById(id).map(conditioner -> new ConditionerDTO(conditioner)).orElse(null);
    }

    @PostMapping("/admin/conditioners") //Add new conditioner to the list ADMIN
    public ResponseEntity<Object> registerNewSConditioner(@RequestBody ProductsDataADMIN conditionerData){

        if(conditionerData.getName().isEmpty() || conditionerData.getDescription().isEmpty() || conditionerData.getUrl().isEmpty() || String.valueOf(conditionerData.getPrice()).isEmpty() || String.valueOf(conditionerData.getStock()).isEmpty()){
            return new ResponseEntity<>("Por favor, rellene todos los campos", HttpStatus.FORBIDDEN);
        }

        if(conditionerData.getPrice() == 0 || conditionerData.getPrice() <= 0){
            return new ResponseEntity<>("El monto del producto debe ser mayor a cero", HttpStatus.FORBIDDEN);
        }

        conditionerRepository.save(new Conditioner(conditionerData.getName(), conditionerData.getDescription(), conditionerData.getUrl(), conditionerData.getPrice(), conditionerData.getStock()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/admin/conditioners/{id}")
    public ResponseEntity<Object> changeDataConditioner(
            @RequestBody ProductsDataADMIN conditionerData,
            @PathVariable Long id){

        Conditioner conditioner = conditionerRepository.findById(id).orElse(null);

        if(conditionerData.getPrice() == 0 || conditionerData.getPrice() <= 0){
            return new ResponseEntity<>("El monto del producto debe ser mayor a cero", HttpStatus.FORBIDDEN);
        }

        conditioner.setName(conditionerData.getName());
        conditioner.setDescription(conditionerData.getDescription());
        conditioner.setUrl(conditionerData.getUrl());
        conditioner.setPrice(conditionerData.getPrice());
        conditioner.setStock(conditionerData.getStock());

        conditionerRepository.save(conditioner);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/conditioners/{id}")
    public ResponseEntity<Object> deleteConditioner(@PathVariable Long id){
        Conditioner conditioner = conditionerRepository.findById(id).orElse(null);

        conditionerRepository.deleteById(conditioner.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
