package com.project.elmejorcombo.dtos;

import com.project.elmejorcombo.models.Conditioner;

public class ConditionerDTO {

    private Long id;
    private String name;
    private String description;
    private String url;
    private double price;
    private int stock;

    //CONSTRUCTORES
    public ConditionerDTO() { }

    public ConditionerDTO(Conditioner conditioner) {
        this.id = conditioner.getId();
        this.name = conditioner.getName();
        this.description = conditioner.getDescription();
        this.url = conditioner.getUrl();
        this.price = conditioner.getPrice();
        this.stock = conditioner.getStock();
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
