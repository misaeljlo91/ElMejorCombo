package com.project.elmejorcombo.controllers;

import com.project.elmejorcombo.models.Client;
import com.project.elmejorcombo.repositories.ClientRepository;
import com.project.elmejorcombo.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/forgot-data")
    public ResponseEntity<Object> forgotUserdata(
            @RequestParam String email){

        Client client = clientRepository.findByEmail(email);

        if(client == null){
            return new ResponseEntity<>("Correo electrónico no registrado. Intente nuevamente.", HttpStatus.FORBIDDEN);
        }

        String subject = "Solicitud de cambio de datos";

        String text1 = "¡Hola " + client.getFirstname() + "! Hemos recibido tu solicitud para realizar el cambio de dato que fue olvidado. Por favor ingrese en el enlace que se le envía para continuar con su solicitud.\n\n";
        String text2 = "Aquí va el enlace\n\n";
        String text3 = "Para mayor información, no dudes en ponerte en contacto con nosotros. Te dejamos nuestra redes sociales para que te comuniques.\n\n";
        String text4 = "Aquí van las redes sociales";

        String message = text1 + text2 + text3 + text4;

        mailService.sendEmail(email, subject, message);
        return new ResponseEntity<>("Correo enviado.", HttpStatus.OK);
    }
}
