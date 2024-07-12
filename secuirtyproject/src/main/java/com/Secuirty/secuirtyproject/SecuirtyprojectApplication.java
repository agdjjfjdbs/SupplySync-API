package com.Secuirty.secuirtyproject;

import com.Secuirty.secuirtyproject.Entities.Role;
import com.Secuirty.secuirtyproject.Entities.User;
import com.Secuirty.secuirtyproject.Repository.UserRepository;
import com.Secuirty.secuirtyproject.Services.Implementation.EncryptImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecuirtyprojectApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SecuirtyprojectApplication.class, args);
	}

	public void run(String... args) {
		User adminAccount = userRepository.findByRole(Role.Admin);
		if (null == adminAccount) {
			User user = new User();
			user.setEmail(EncryptImpl.encrypt("karim@gmail.com"));
			user.setFirstname("admin");
			user.setLastname("admin");
			user.setRole(Role.Admin);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
