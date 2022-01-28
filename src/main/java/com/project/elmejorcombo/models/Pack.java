package com.project.elmejorcombo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String code;
    private String namePack;
    private PackClient typeClient;
    private double price;

    @OneToMany(mappedBy = "pack", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<PackProduct> packProducts = new HashSet<>();

    //CONSTRUCTORES
    public Pack() { }

    public Pack(String code, String namePack, PackClient typeClient, double price) {
        this.code = code;
        this.namePack = namePack;
        this.typeClient = typeClient;
        this.price = price;
    }

    //GETTER
    public Long getId() {return id;}
    public String getCode() {return code;}
    public String getNamePack() {return namePack;}
    public PackClient getTypeClient() {return typeClient;}
    public double getPrice() {return price;}
    public Set<PackProduct> getPackProducts() {return packProducts;}

    //SETTER
    public void setId(Long id) {this.id = id;}
    public void setCode(String code) {this.code = code;}
    public void setNamePack(String namePack) {this.namePack = namePack;}
    public void setTypeClient(PackClient typeClient) {this.typeClient = typeClient;}
    public void setPrice(double price) {this.price = price;}
    public void setPackProducts(Set<PackProduct> packProducts) {this.packProducts = packProducts;}

    //METHOD-GET
    @JsonIgnore
    public List<Shampoo> getShampoos() {
        return packProducts.stream().map(packProductShampoo -> packProductShampoo.getShampoo()).collect(Collectors.toList());
    }

    @JsonIgnore
    public List<Conditioner> getConditioners() {
        return packProducts.stream().map(packProductConditioner -> packProductConditioner.getConditioner()).collect(Collectors.toList());
    }

    @JsonIgnore
    public List<Soap> getSoaps() {
        return packProducts.stream().map(packProductSoap -> packProductSoap.getSoap()).collect(Collectors.toList());
    }
}

