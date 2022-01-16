package com.project.elmejorcombo.requestBodies;

import com.project.elmejorcombo.models.Conditioner;
import com.project.elmejorcombo.models.Shampoo;
import com.project.elmejorcombo.models.Soap;

public class PackProductADMIN {

    private String namePack;
    private Shampoo shampoo;
    private Conditioner conditioner;
    private Soap soap;
    private double price;

    //CONSTRUCTORES
    public PackProductADMIN() { }

    public PackProductADMIN(String namePack, Shampoo shampoo, Conditioner conditioner, Soap soap, double price) {
        this.namePack = namePack;
        this.shampoo = shampoo;
        this.conditioner = conditioner;
        this.soap = soap;
        this.price = price;
    }

    //GETTER
    public String getNamePack() {return namePack;}
    public Shampoo getShampoo() {return shampoo;}
    public Conditioner getConditioner() {return conditioner;}
    public Soap getSoap() {return soap;}
    public double getPrice() {return price;}

    //SETTER
    public void setNamePack(String namePack) {this.namePack = namePack;}
    public void setShampoo(Shampoo shampoo) {this.shampoo = shampoo;}
    public void setConditioner(Conditioner conditioner) {this.conditioner = conditioner;}
    public void setSoap(Soap soap) {this.soap = soap;}
    public void setPrice(double price) {this.price = price;}
}
