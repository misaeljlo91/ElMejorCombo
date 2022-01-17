package com.project.elmejorcombo.dtos;

import com.project.elmejorcombo.models.Conditioner;
import com.project.elmejorcombo.models.PackProduct;
import com.project.elmejorcombo.models.Shampoo;
import com.project.elmejorcombo.models.Soap;

public class PackProductDTO {

    private Long id;
    private String code;
    private String namePack;
    private Shampoo shampoo;
    private Conditioner conditioner;
    private Soap soap;
    private double price;


    //CONSTRUCTORES
    public PackProductDTO() { }

    public PackProductDTO(PackProduct packProduct) {
        this.id = packProduct.getId();
        this.code = packProduct.getPack().getCode();
        this.namePack = packProduct.getPack().getNamePack();
        this.shampoo = packProduct.getShampoo();
        this.conditioner = packProduct.getConditioner();
        this.soap = packProduct.getSoap();
        this.price = packProduct.getPack().getPrice();
    }

    //GETTER
    public Long getId() {return id;}
    public String getCode() {return code;}
    public String getNamePack() {return namePack;}
    public Shampoo getShampoo() {return shampoo;}
    public Conditioner getConditioner() {return conditioner;}
    public Soap getSoap() {return soap;}
    public double getPrice() {return price;}

    //SETTER
    public void setId(Long id) {this.id = id;}
    public void setCode(String code) {this.code = code;}
    public void setNamePack(String namePack) {this.namePack = namePack;}
    public void setShampoo(Shampoo shampoo) {this.shampoo = shampoo;}
    public void setConditioner(Conditioner conditioner) {this.conditioner = conditioner;}
    public void setSoap(Soap soap) {this.soap = soap;}
    public void setPrice(double price) {this.price = price;}
}
