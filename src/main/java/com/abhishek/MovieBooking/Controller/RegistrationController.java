package com.abhishek.MovieBooking.Controller;

import java.util.Collections;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.abhishek.MovieBooking.Model.Role;
import com.abhishek.MovieBooking.Model.User;
import com.abhishek.MovieBooking.Repository.RoleRepository;
import com.abhishek.MovieBooking.Repository.UserRepository;


@Controller
public class RegistrationController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/register")
	public String showRegistrationPage(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String registrationprocess(User user) throws Exception{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if(userRepository.existsByUsername(user.getUsername())) {
			throw new AuthenticationException("Username is already taken!");
		}
		
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new AuthenticationException("Email is already taken!");
		}
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		Role roles = roleRepository.findByName("USER").get();
		user.setRoles(Collections.singleton(roles));
		
		userRepository.save(user);
		
		return "redirect:/";
		
	}
}
