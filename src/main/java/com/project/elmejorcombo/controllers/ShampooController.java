package com.project.elmejorcombo.controllers;

import com.project.elmejorcombo.dtos.ShampooDTO;
import com.project.elmejorcombo.models.Shampoo;
import com.project.elmejorcombo.repositories.ShampooRepository;
import com.project.elmejorcombo.requestBodies.ProductsDataADMIN;
import com.project.elmejorcombo.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ShampooController {

    @Autowired
    private ShampooService shampooService;

    @Autowired
    private ShampooRepository shampooRepository;

    //API Rest ADMIN
    @GetMapping("/admin/shampoos") //Get list shampoos ADMIN only
    public Page<Shampoo> getAllShampoos(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<Shampoo> listShampoos = shampooService.findAll(pageable);
        return listShampoos;
    }

    @GetMapping("/admin/shampoos/{id}") //Get shampoo by ID ADMIN only
    public ShampooDTO getShampooID(@PathVariable Long id){
        return shampooRepository.findById(id).map(shampoo -> new ShampooDTO(shampoo)).orElse(null);
    }

    @PostMapping("/admin/shampoos") //Add new shampoo to the list ADMIN
    public ResponseEntity<Object> registerNewShampoo(@RequestBody ProductsDataADMIN shampooData){

        if(shampooData.getName().isEmpty() || shampooData.getDescription().isEmpty() || shampooData.getUrl().isEmpty() || String.valueOf(shampooData.getPrice()).isEmpty() || String.valueOf(shampooData.getStock()).isEmpty()){
            return new ResponseEntity<>("Por favor, rellene todos los campos", HttpStatus.FORBIDDEN);
        }

        if(shampooData.getPrice() == 0 || shampooData.getPrice() <= 0){
            return new ResponseEntity<>("El monto del producto debe ser mayor a cero", HttpStatus.FORBIDDEN);
        }

        shampooRepository.save(new Shampoo(shampooData.getName(), shampooData.getDescription(), shampooData.getUrl(), shampooData.getPrice(), shampooData.getStock()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/admin/shampoos/{id}")
    public ResponseEntity<Object> changeDataShampoo(
            @RequestBody ProductsDataADMIN shampooData,
            @PathVariable Long id){

        Shampoo shampoo = shampooRepository.findById(id).orElse(null);

        if(shampooData.getPrice() == 0 || shampooData.getPrice() <= 0){
            return new ResponseEntity<>("El monto del producto debe ser mayor a cero", HttpStatus.FORBIDDEN);
        }

        shampoo.setName(shampooData.getName());
        shampoo.setDescription(shampooData.getDescription());
        shampoo.setUrl(shampooData.getUrl());
        shampoo.setPrice(shampooData.getPrice());
        shampoo.setStock(shampooData.getStock());

        shampooRepository.save(shampoo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/shampoos/{id}")
    public ResponseEntity<Object> deleteShampoo(@PathVariable Long id){
        Shampoo shampoo = shampooRepository.findById(id).orElse(null);

        shampooRepository.deleteById(shampoo.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
