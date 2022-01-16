package com.project.elmejorcombo.dtos;

import com.project.elmejorcombo.models.Soap;

import java.util.Set;
import java.util.stream.Collectors;

public class SoapDTO {

    private Long id;
    private String code;
    private String name;
    private String description;
    private String url;
    private double price;
    private int stock;
    private Set<PackProductDTO> packProducts;

    //CONSTRUCTORES
    public SoapDTO() { }

    public SoapDTO(Soap soap) {
        this.id = soap.getId();
        this.code = soap.getCode();
        this.name = soap.getName();
        this.description = soap.getDescription();
        this.url = soap.getUrl();
        this.price = soap.getPrice();
        this.stock = soap.getStock();
        this.packProducts = soap.getPackProducts().stream().map(PackProductDTO::new).collect(Collectors.toSet());
    }

    //GETTER
    public Long getId() {return id;}
    public String getCode() {return code;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public String getUrl() {return url;}
    public double getPrice() {return price;}
    public int getStock() {return stock;}
    public Set<PackProductDTO> getPackProducts() {return packProducts;}

    //SETTER
    public void setId(Long id) {this.id = id;}
    public void setCode(String code) {this.code = code;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setUrl(String url) {this.url = url;}
    public void setPrice(double price) {this.price = price;}
    public void setStock(int stock) {this.stock = stock;}
}
