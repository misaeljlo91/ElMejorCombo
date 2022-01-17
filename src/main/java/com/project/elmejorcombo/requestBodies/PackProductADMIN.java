package com.project.elmejorcombo.requestBodies;

public class PackProductADMIN {

    private String namePack;
    private Long idShampoo;
    private Long idConditioner;
    private Long idSoap;
    private double price;

    //CONSTRUCTORES
    public PackProductADMIN() { }

    public PackProductADMIN(String namePack, Long idShampoo, Long idConditioner, Long idSoap, double price) {
        this.namePack = namePack;
        this.idShampoo = idShampoo;
        this.idConditioner = idConditioner;
        this.idSoap = idSoap;
        this.price = price;
    }

    //GETTER
    public String getNamePack() {return namePack;}
    public Long getIdShampoo() {return idShampoo;}
    public Long getIdConditioner() {return idConditioner;}
    public Long getIdSoap() {return idSoap;}
    public double getPrice() {return price;}

    //SETTER
    public void setNamePack(String namePack) {this.namePack = namePack;}
    public void setIdShampoo(Long idShampoo) {this.idShampoo = idShampoo;}
    public void setIdConditioner(Long idConditioner) {this.idConditioner = idConditioner;}
    public void setIdSoap(Long idSoap) {this.idSoap = idSoap;}
    public void setPrice(double price) {this.price = price;}
}
