package com.project.elmejorcombo.requestBodies;

import com.project.elmejorcombo.models.PackClient;

public class PackProductADMIN {

    private String namePack;
    private Long idShampoo;
    private Long idConditioner;
    private Long idSoap;
    private PackClient typeClient;
    private double price;

    //CONSTRUCTORES
    public PackProductADMIN() { }

    public PackProductADMIN(String namePack, Long idShampoo, Long idConditioner, Long idSoap, PackClient typeClient, double price) {
        this.namePack = namePack;
        this.idShampoo = idShampoo;
        this.idConditioner = idConditioner;
        this.idSoap = idSoap;
        this.typeClient = typeClient;
        this.price = price;
    }

    //GETTER
    public String getNamePack() {return namePack;}
    public Long getIdShampoo() {return idShampoo;}
    public Long getIdConditioner() {return idConditioner;}
    public Long getIdSoap() {return idSoap;}
    public PackClient getTypeClient() {return typeClient;}
    public double getPrice() {return price;}

    //SETTER
    public void setNamePack(String namePack) {this.namePack = namePack;}
    public void setIdShampoo(Long idShampoo) {this.idShampoo = idShampoo;}
    public void setIdConditioner(Long idConditioner) {this.idConditioner = idConditioner;}
    public void setIdSoap(Long idSoap) {this.idSoap = idSoap;}
    public void setTypeClient(PackClient typeClient) {this.typeClient = typeClient;}
    public void setPrice(double price) {this.price = price;}
}
