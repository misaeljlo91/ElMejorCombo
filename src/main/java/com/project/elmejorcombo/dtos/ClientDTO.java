package com.project.elmejorcombo.dtos;

import com.project.elmejorcombo.models.Client;
import com.project.elmejorcombo.models.ClientRole;

public class ClientDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private  String username;
    private String email;
    private String password;
    //private String profile;
    private ClientRole role;

    //CONSTRUCTORES
    public ClientDTO() { }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstname = client.getFirstname();
        this.lastname = client.getLastname();
        this.username = client.getUsername();
        this.email = client.getEmail();
        this.password = client.getPassword();
        //this.profile = client.getProfile();
        this.role = client.getRole();
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
