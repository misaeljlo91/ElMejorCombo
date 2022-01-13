package com.project.elmejorcombo.dtos;

import com.project.elmejorcombo.models.Shampoo;

public class ShampooDTO {

    private Long id;
    private String name;
    private String description;
    private String url;
    private double price;
    private int stock;

    //CONSTRUCTORES
    public ShampooDTO() { }

    public ShampooDTO(Shampoo shampoo) {
        this.id = shampoo.getId();
        this.name = shampoo.getName();
        this.description = shampoo.getDescription();
        this.url = shampoo.getUrl();
        this.price = shampoo.getPrice();
        this.stock = shampoo.getStock();
    }

    //GETTER
    public Long getId() {return id;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public String getUrl() {return url;}
    public double getPrice() {return price;}
    public int getStock() {return stock;}

    //SETTER
    public void setId(Long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setUrl(String url) {this.url = url;}
    public void setPrice(double price) {this.price = price;}
    public void setStock(int stock) {this.stock = stock;}
}
