package com.project.elmejorcombo.controllers;

import com.project.elmejorcombo.dtos.PackProductDTO;
import com.project.elmejorcombo.models.*;
import com.project.elmejorcombo.repositories.*;
import com.project.elmejorcombo.requestBodies.PackProductADMIN;
import com.project.elmejorcombo.services.PackProductService;
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
public class PackProductController {

    @Autowired
    private PackProductService packProductService;

    @Autowired
    private PackProductRepository packProductRepository;

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private ShampooRepository shampooRepository;

    @Autowired
    private ConditionerRepository conditionerRepository;

    @Autowired
    private SoapRepository soapRepository;

    //API Rest ADMIN
    @GetMapping("/admin/packs") //Get list packs ADMIN only
    public List<PackProductDTO> getAllPacks(){
        return packProductRepository.findAll().stream().map(packProduct -> new PackProductDTO(packProduct)).collect(Collectors.toList());
    }
    /*public Page<PackProduct> getAllPacks(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<PackProduct> listPacks = packProductService.findAll(pageable);
        return listPacks;
    }*/

    @GetMapping("/admin/packs/{id}") //Get pack by ID ADMIN only
    public PackProductDTO getPackID(@PathVariable Long id){
        return packProductRepository.findById(id).map(packProduct -> new PackProductDTO(packProduct)).orElse(null);
    }

    @PostMapping("/admin/packs") //Add new pack to the list ADMIN
    public ResponseEntity<Object> registerNewPack(@RequestBody PackProductADMIN packProduct){

        Shampoo shampoo = shampooRepository.findById(packProduct.getIdShampoo()).orElse(null);
        Conditioner conditioner = conditionerRepository.findById(packProduct.getIdConditioner()).orElse(null);
        Soap soap = soapRepository.findById(packProduct.getIdSoap()).orElse(null);

        if(shampoo == null){
            return new ResponseEntity<>("El nombre del champú ingresado no existe", HttpStatus.FORBIDDEN);
        }

        if(conditioner == null){
            return new ResponseEntity<>("El nombre del acondicionador ingresado no existe", HttpStatus.FORBIDDEN);
        }

        if(soap == null){
            return new ResponseEntity<>("El nombre del jabón ingresado no existe", HttpStatus.FORBIDDEN);
        }

        if(packProduct.getNamePack().isEmpty() || String.valueOf(packProduct.getPrice()).isEmpty()){
            return new ResponseEntity<>("Por favor, rellene todos los campos", HttpStatus.FORBIDDEN);
        }

        if(packProduct.getPrice() == 0 || packProduct.getPrice() < 0){
            return new ResponseEntity<>("El monto del producto debe ser mayor a cero", HttpStatus.FORBIDDEN);
        }

        int code = (int)((Math.random()*(999999-100000+1))+100000);

        Pack newPack = new Pack("P"+code, packProduct.getNamePack(), packProduct.getPrice());

        packRepository.save(newPack);
        packProductRepository.save(new PackProduct(newPack, shampoo, conditioner, soap));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/admin/packs/{id}")
    public ResponseEntity<Object> changeDataADMIN(
            @RequestBody PackProductADMIN packProduct,
            @PathVariable Long id){

        PackProduct pack = packProductRepository.findById(id).orElse(null);

        Shampoo shampoo = shampooRepository.findById(packProduct.getIdShampoo()).orElse(null);
        Conditioner conditioner = conditionerRepository.findById(packProduct.getIdConditioner()).orElse(null);
        Soap soap = soapRepository.findById(packProduct.getIdSoap()).orElse(null);

        if(shampoo == null){
            return new ResponseEntity<>("El nombre del champú ingresado no existe", HttpStatus.FORBIDDEN);
        }

        if(conditioner == null){
            return new ResponseEntity<>("El nombre del acondicionador ingresado no existe", HttpStatus.FORBIDDEN);
        }

        if(soap == null){
            return new ResponseEntity<>("El nombre del jabón ingresado no existe", HttpStatus.FORBIDDEN);
        }

        if(packProduct.getPrice() == 0 || packProduct.getPrice() < 0){
            return new ResponseEntity<>("El monto del producto debe ser mayor a cero", HttpStatus.FORBIDDEN);
        }

        pack.getPack().setNamePack(packProduct.getNamePack());
        pack.setShampoo(shampoo);
        pack.setConditioner(conditioner);
        pack.setSoap(soap);
        pack.getPack().setPrice(packProduct.getPrice());

        packProductRepository.save(pack);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/packs/{id}")
    public ResponseEntity<Object> deletePackADMIN(@PathVariable Long id){
        PackProduct pack = packProductRepository.findById(id).orElse(null);

        packProductRepository.deleteById(pack.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //API Rest ALL
}
