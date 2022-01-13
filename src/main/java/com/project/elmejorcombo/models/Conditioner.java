package com.project.elmejorcombo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conditioner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private String description;
    private String url;
    private double price;
    private int stock;

    //CONSTRUCTORES
    public Conditioner() { }

    public Conditioner(String name, String description, String url, double price, int stock) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.price = price;
        this.stock = stock;
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
