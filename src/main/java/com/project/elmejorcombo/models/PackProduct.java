package com.project.elmejorcombo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PackProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pack_id")
    private Pack pack;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shampoo_id")
    private Shampoo shampoo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conditioner_id")
    private Conditioner conditioner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "soap_id")
    private Soap soap;

    //CONSTRUCTORES
    public PackProduct() { }

    public PackProduct(String code, Pack pack, Shampoo shampoo, Conditioner conditioner, Soap soap) {
        this.code = code;
        this.pack = pack;
        this.shampoo = shampoo;
        this.conditioner = conditioner;
        this.soap = soap;
    }

    //GETTER
    public Long getId() {return id;}
    public String getCode() {return code;}

    @JsonIgnore
    public Pack getPack() {return pack;}

    @JsonIgnore
    public Shampoo getShampoo() {return shampoo;}

    @JsonIgnore
    public Conditioner getConditioner() {return conditioner;}

    @JsonIgnore
    public Soap getSoap() {return soap;}

    //SETTER
    public void setId(Long id) {this.id = id;}
    public void setCode(String code) {this.code = code;}
    public void setPack(Pack pack) {this.pack = pack;}
    public void setShampoo(Shampoo shampoo) {this.shampoo = shampoo;}
    public void setConditioner(Conditioner conditioner) {this.conditioner = conditioner;}
    public void setSoap(Soap soap) {this.soap = soap;}
}
