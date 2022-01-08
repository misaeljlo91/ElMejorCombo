package com.project.elmejorcombo.requestBodies;

import com.project.elmejorcombo.models.ClientRole;

public class ClientDataRequest {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;

    //CONSTRUCTORES
    public ClientDataRequest() { }

    public ClientDataRequest(String firstname, String lastname, String username, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //GETTER
    public String getFirstname() {return firstname;}
    public String getLastname() {return lastname;}
    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}

    //SETTER
    public void setFirstname(String firstname) {this.firstname = firstname;}
    public void setLastname(String lastname) {this.lastname = lastname;}
    public void setUsername(String username) {this.username = username;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
}
