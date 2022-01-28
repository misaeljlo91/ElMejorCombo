package com.project.elmejorcombo.dtos;

import com.project.elmejorcombo.models.Pack;
import com.project.elmejorcombo.models.PackClient;

import java.util.Set;
import java.util.stream.Collectors;

public class PackDTO {

    private Long id;
    private String code;
    private String namePack;
    private PackClient typeClient;
    private double price;
    private Set<PackProductDTO> packProducts;

    //CONSTRUCTORES
    public PackDTO() { }

    public PackDTO(Pack pack) {
        this.id = pack.getId();
        this.code = pack.getCode();
        this.namePack = pack.getNamePack();
        this.typeClient = pack.getTypeClient();
        this.price = pack.getPrice();
        this.packProducts = pack.getPackProducts().stream().map(PackProductDTO::new).collect(Collectors.toSet());
    }

    //GETTER
    public Long getId() {return id;}
    public String getCode() {return code;}
    public String getNamePack() {return namePack;}
    public PackClient getTypeClient() {return typeClient;}
    public double getPrice() {return price;}
    public Set<PackProductDTO> getPackProducts() {return packProducts;}

    //SETTER
    public void setId(Long id) {this.id = id;}
    public void setCode(String code) {this.code = code;}
    public void setNamePack(String namePack) {this.namePack = namePack;}
    public void setTypeClient(PackClient typeClient) {this.typeClient = typeClient;}
    public void setPrice(double price) {this.price = price;}
}
