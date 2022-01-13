package com.project.elmejorcombo.requestBodies;

public class ProductsDataADMIN {

    private String name;
    private String description;
    private String url;
    private double price;
    private int stock;

    //CONSTRUCTORES
    public ProductsDataADMIN() { }

    public ProductsDataADMIN(String name, String description, String url, double price, int stock) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.price = price;
        this.stock = stock;
    }

    //GETTER
    public String getName() {return name;}
    public String getDescription() {return this.description;}
    public String getUrl() {return url;}
    public double getPrice() {return price;}
    public int getStock() {return stock;}

    //SETTER
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setUrl(String url) {this.url = url;}
    public void setPrice(double price) {this.price = price;}
    public void setStock(int stock) {this.stock = stock;}
}
