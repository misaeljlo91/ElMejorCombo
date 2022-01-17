package com.project.elmejorcombo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Soap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String code;
    private String name;
    private String description;
    private String url;
    private double price;
    private int stock;

    @OneToMany(mappedBy = "soap", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<PackProduct> packProducts = new HashSet<>();

    //CONSTRUCTORES
    public Soap() { }

    public Soap(String code, String name, String description, String url, double price, int stock) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.url = url;
        this.price = price;
        this.stock = stock;
    }

    //GETTER
    public Long getId() {return id;}
    public String getCode() {return code;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public String getUrl() {return url;}
    public double getPrice() {return price;}
    public int getStock() {return stock;}
    public Set<PackProduct> getPackProducts() {return packProducts;}

    //SETTER
    public void setId(Long id) {this.id = id;}
    public void setCode(String code) {this.code = code;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setUrl(String url) {this.url = url;}
    public void setPrice(double price) {this.price = price;}
    public void setStock(int stock) {this.stock = stock;}
    public void setPackProducts(Set<PackProduct> packProducts) {this.packProducts = packProducts;}

    //METHOD-GET
    @JsonIgnore
    public List<Pack> getPacks() {
        return packProducts.stream().map(packProduct -> packProduct.getPack()).collect(Collectors.toList());
    }
}
