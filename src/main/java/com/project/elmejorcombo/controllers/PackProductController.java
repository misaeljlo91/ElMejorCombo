package com.project.elmejorcombo.controllers;

import com.project.elmejorcombo.dtos.PackProductDTO;
import com.project.elmejorcombo.models.Pack;
import com.project.elmejorcombo.models.PackProduct;
import com.project.elmejorcombo.repositories.PackProductRepository;
import com.project.elmejorcombo.repositories.PackRepository;
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

        if(packProduct.getNamePack().isEmpty() || packProduct.getShampoo() == null || packProduct.getConditioner() == null || packProduct.getSoap() == null ||String.valueOf(packProduct.getPrice()).isEmpty()){
            return new ResponseEntity<>("Por favor, rellene todos los campos", HttpStatus.FORBIDDEN);
        }

        if(packProduct.getPrice() == 0 || packProduct.getPrice() < 0){
            return new ResponseEntity<>("El monto del producto debe ser mayor a cero", HttpStatus.FORBIDDEN);
        }

        int code = (int)((Math.random()*(999999-100000+1))+100000);

        Pack newPack = new Pack(packProduct.getNamePack(), packProduct.getPrice());

        packRepository.save(newPack);
        packProductRepository.save(new PackProduct("P"+code, newPack, packProduct.getShampoo(), packProduct.getConditioner(), packProduct.getSoap()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/admin/packs/{id}")
    public ResponseEntity<Object> changeDataADMIN(
            @RequestBody PackProductADMIN packProduct,
            @PathVariable Long id){

        PackProduct pack = packProductRepository.findById(id).orElse(null);

        if(packProduct.getPrice() == 0 || packProduct.getPrice() < 0){
            return new ResponseEntity<>("El monto del producto debe ser mayor a cero", HttpStatus.FORBIDDEN);
        }

        pack.getPack().setNamePack(packProduct.getNamePack());
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
