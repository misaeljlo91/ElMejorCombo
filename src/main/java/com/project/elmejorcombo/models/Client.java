package com.project.elmejorcombo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String firstname;
    private String lastname;
    private  String username;
    private String email;
    private String password;
    //private String profile; //Para agregar foto de perfil
    private ClientRole role;

    //CONSTRUCTORES
    public Client() { }

    public Client(String firstname, String lastname, String username, String email, String password, ClientRole role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        //this.profile = profile;
        this.role = role;
    }

    //GETTER
    public Long getId() {return id;}
    public String getFirstname() {return firstname;}
    public String getLastname() {return lastname;}
    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    //public String getProfile() {return profile;}
    public ClientRole getRole() {return role;}

    //SETTER
    public void setId(Long id) {this.id = id;}
    public void setFirstname(String firstname) {this.firstname = firstname;}
    public void setLastname(String lastname) {this.lastname = lastname;}
    public void setUsername(String username) {this.username = username;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    //public void setProfile(String profile) {this.profile = profile;}
    public void setRole(ClientRole role) {this.role = role;}
}
