package com.project.elmejorcombo.controllers;

import com.project.elmejorcombo.dtos.SoapDTO;
import com.project.elmejorcombo.models.Soap;
import com.project.elmejorcombo.repositories.SoapRepository;
import com.project.elmejorcombo.requestBodies.ProductsDataADMIN;
import com.project.elmejorcombo.services.SoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SoapController {

    @Autowired
    private SoapService soapService;

    @Autowired
    private SoapRepository soapRepository;

    //API Rest ADMIN
    @GetMapping("/admin/soaps") //Get list soaps ADMIN only
    public List<SoapDTO>  getAllSoaps(){
        return soapRepository.findAll().stream().map(soap -> new SoapDTO(soap)).collect(Collectors.toList());
    }
    /*public Page<Soap> getAllSoaps(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<Soap> listSoaps = soapService.findAll(pageable);
        return listSoaps;
    }*/

    @GetMapping("/admin/soaps/{id}") //Get soap by ID ADMIN only
    public SoapDTO getSoapID(@PathVariable Long id){
        return soapRepository.findById(id).map(soap -> new SoapDTO(soap)).orElse(null);
    }

    @PostMapping("/admin/soaps") //Add new soap to the list ADMIN
    public ResponseEntity<Object> registerNewSoap(@RequestBody ProductsDataADMIN soapData){

        if(soapData.getName().isEmpty() || soapData.getDescription().isEmpty() || soapData.getUrl().isEmpty() || String.valueOf(soapData.getPrice()).isEmpty() || String.valueOf(soapData.getStock()).isEmpty()){
            return new ResponseEntity<>("Por favor, rellene todos los campos", HttpStatus.FORBIDDEN);
        }

        if(soapData.getPrice() == 0 || soapData.getPrice() <= 0){
            return new ResponseEntity<>("El monto del producto debe ser mayor a cero", HttpStatus.FORBIDDEN);
        }

        int code = (int)((Math.random()*(999999-100000+1))+100000);

        soapRepository.save(new Soap("J"+code, soapData.getName(), soapData.getDescription(), soapData.getUrl(), soapData.getPrice(), soapData.getStock()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/admin/soaps/{id}")
    public ResponseEntity<Object> changeDataSoap(
            @RequestBody ProductsDataADMIN soapData,
            @PathVariable Long id){

        Soap soap = soapRepository.findById(id).orElse(null);

        if(soapData.getPrice() == 0 || soapData.getPrice() <= 0){
            return new ResponseEntity<>("El monto del producto debe ser mayor a cero", HttpStatus.FORBIDDEN);
        }

        soap.setName(soapData.getName());
        soap.setDescription(soapData.getDescription());
        soap.setUrl(soapData.getUrl());
        soap.setPrice(soapData.getPrice());
        soap.setStock(soapData.getStock());

        soapRepository.save(soap);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/soaps/{id}")
    public ResponseEntity<Object> deleteSoap(@PathVariable Long id){
        Soap soap = soapRepository.findById(id).orElse(null);

        soapRepository.deleteById(soap.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
