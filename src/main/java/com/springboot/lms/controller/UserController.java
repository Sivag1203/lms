package com.springboot.lms.controller;

import java.security.Principal;

import com.springboot.lms.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.User;
import com.springboot.lms.service.UserService;
import com.springboot.lms.util.JwtUtil;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@PostMapping("/signup")
	public User signUp(@RequestBody User user) {
		return userService.signUp(user);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginRequest) {
		try {
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());

			if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
				String token = jwtUtil.createToken(userDetails.getUsername());
				return ResponseEntity.ok(token);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
		}
	}

	@GetMapping("/token")
	public ResponseEntity<?> getToken(Principal principal) {
		try {
			String token = jwtUtil.createToken(principal.getName());
			return ResponseEntity.status(HttpStatus.OK).body(token);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
}


