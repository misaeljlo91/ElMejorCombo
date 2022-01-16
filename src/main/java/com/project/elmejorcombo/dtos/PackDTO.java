package com.project.elmejorcombo.dtos;

import com.project.elmejorcombo.models.Pack;

import java.util.Set;
import java.util.stream.Collectors;

public class PackDTO {

    private Long id;
    private String namePack;
    private double price;
    private Set<PackProductDTO> packProducts;

    //CONSTRUCTORES
    public PackDTO() { }

    public PackDTO(Pack pack) {
        this.id = pack.getId();
        this.namePack = pack.getNamePack();
        this.price = pack.getPrice();
        this.packProducts = pack.getPackProducts().stream().map(PackProductDTO::new).collect(Collectors.toSet());
    }

    //GETTER
    public Long getId() {return id;}
    public String getNamePack() {return namePack;}
    public double getPrice() {return price;}
    public Set<PackProductDTO> getPackProducts() {return packProducts;}

    //SETTER
    public void setId(Long id) {this.id = id;}
    public void setNamePack(String namePack) {this.namePack = namePack;}
    public void setPrice(double price) {this.price = price;}
}
