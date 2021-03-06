package com.project.elmejorcombo.controllers;

import com.project.elmejorcombo.dtos.ClientDTO;
import com.project.elmejorcombo.models.Client;
import com.project.elmejorcombo.models.ClientRole;
import com.project.elmejorcombo.repositories.ClientRepository;
import com.project.elmejorcombo.requestBodies.ClientDataADMIN;
import com.project.elmejorcombo.requestBodies.ClientDataRequest;
import com.project.elmejorcombo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //API Rest ADMIN
    @GetMapping("/admin/clients") //Get list client ADMIN only
    public List<ClientDTO> getAllClients(){
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }
    /*public Page<Client> getAllClients(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<Client> listClients = clientService.findAll(pageable);
        return listClients;
    }*/

    @GetMapping("/admin/clients/{id}") //Get client by ID ADMIN only
    public ClientDTO getClientID(@PathVariable Long id){
        return clientRepository.findById(id).map(client -> new ClientDTO(client)).orElse(null);
    }

    @PutMapping("/admin/clients/{id}")
    public ResponseEntity<Object> changeDataADMIN(
            @RequestBody ClientDataADMIN clientData,
            @PathVariable Long id){

        Client client = clientRepository.findById(id).orElse(null);

        client.setFirstname(clientData.getFirstname());
        client.setLastname(clientData.getLastname());
        client.setUsername(clientData.getUsername());
        client.setEmail(clientData.getEmail());
        client.setRole(clientData.getRole());

        clientRepository.save(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/clients/{id}")
    public ResponseEntity<Object> deleteClientADMIN(@PathVariable Long id){
        Client client = clientRepository.findById(id).orElse(null);

        clientRepository.deleteById(client.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //API Rest ALL
    @PostMapping("/clients")
    public ResponseEntity<Object> registerNewClient(@RequestBody ClientDataRequest clientData){

        if(clientData.getFirstname().isEmpty() || clientData.getLastname().isEmpty() || clientData.getUsername().isEmpty() || clientData.getEmail().isEmpty() || clientData.getPassword().isEmpty()){
            return new ResponseEntity<>("Por favor, rellene todos los campos", HttpStatus.FORBIDDEN);
        }

        if(clientRepository.findByUsername(clientData.getUsername()) != null){
            return new ResponseEntity<>("Nombre de usuario en uso, intente nuevamente", HttpStatus.FORBIDDEN);
        }

        if(clientRepository.findByEmail(clientData.getEmail()) != null){
            return new ResponseEntity<>("Correo  eletr??nico en uso, intente nuevamente", HttpStatus.FORBIDDEN);
        }

        if(clientData.getPassword().length() < 8 || clientData.getPassword().length() > 16){
            return new ResponseEntity<>("Su contrase??a debe contener entre 8 y 16 caracteres", HttpStatus.FORBIDDEN);
        }

        int code = (int)((Math.random()*(999999-100000+1))+100000);

        clientRepository.save(new Client("CR"+code, clientData.getFirstname(), clientData.getLastname(), clientData.getUsername(), clientData.getEmail(), passwordEncoder.encode(clientData.getPassword()), "/web/assets/profile.png", ClientRole.USER));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/clients/forgot-username")
    public ResponseEntity<Object> forgotUsername(
            @RequestParam String email,
            @RequestParam String username){

        Client client = clientRepository.findByEmail(email);

        if(email.isEmpty() || username.isEmpty()){
            return new ResponseEntity<>("Por favor, rellene todos los campos", HttpStatus.FORBIDDEN);
        }

        if(client == null){
            return new ResponseEntity<>("Correo electr??nico no registrado. Intente nuevamente.", HttpStatus.FORBIDDEN);
        }

        if(clientRepository.findByUsername(username) != null){
            return new ResponseEntity<>("Nombre de usuario en uso, intente nuevamente", HttpStatus.FORBIDDEN);
        }

        client.setUsername(username);

        clientRepository.save(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/clients/forgot-password")
    public ResponseEntity<Object> forgotPassword(
            @RequestParam String email,
            @RequestParam String password){

        Client client = clientRepository.findByEmail(email);

        if(client == null){
            return new ResponseEntity<>("Correo electr??nico no registrado. Intente nuevamente.", HttpStatus.FORBIDDEN);
        }

        if(password.length() < 8 || password.length() > 16){
            return new ResponseEntity<>("Su contrase??a debe contener entre 8 y 16 caracteres", HttpStatus.FORBIDDEN);
        }

        client.setPassword(passwordEncoder.encode(password));

        clientRepository.save(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //API Rest CLIENT CURRENT
    @GetMapping("/clients/current")
    public ClientDTO getClientCurrent(Authentication authentication){
        Client signClient = clientRepository.findByEmail(authentication.getName());
        ClientDTO signClientDTO = new ClientDTO(signClient);
        return signClientDTO;
    }

    @GetMapping("/clients/current/{id}")
    public ClientDTO getClientAuth(
            Authentication authentication,
            @PathVariable Long id){

        Client signClient = clientRepository.findByEmail(authentication.getName());
        return clientRepository.findById(id).map(client1 -> new ClientDTO(client1)).orElse(null);
    }

    //FALTAN SERVICIOS PARA MODIFICAR LOS DATOS
}
