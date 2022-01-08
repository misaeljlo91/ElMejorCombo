package com.project.elmejorcombo;

import com.project.elmejorcombo.models.Client;
import com.project.elmejorcombo.models.ClientRole;
import com.project.elmejorcombo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ElmejorcomboApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElmejorcomboApplication.class);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository){
		return (args) -> {
			Client client1 = new Client("Emmanuel", "Rolón", "emmarolon250", "emmanuelrolon250@gmail.com", passwordEncoder.encode("Indemnizacion1989") , ClientRole.ADMIN);
			Client client2 = new Client("Misael", "López", "misaeljlo91", "misaeljlo91@gmail.com", passwordEncoder.encode("531709Mleo"), ClientRole.ADMIN);

			clientRepository.save(client1);
			clientRepository.save(client2);
		};
	}
}

