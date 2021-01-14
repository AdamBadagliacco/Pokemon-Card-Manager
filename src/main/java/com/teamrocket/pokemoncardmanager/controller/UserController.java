package com.teamrocket.pokemoncardmanager.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.teamrocket.pokemoncardmanager.entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {
	

	@PostMapping("login")
	public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		
		System.out.println("User endpoint hit");
		
		String token = getJWTToken(username);
		User user = new User();
		user.setName(username);
		user.setToken(token);		
		return user;
		
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	@GetMapping("TEST")
	public String test() {
		
		System.out.println("Test 1 Successful");
		
		return "Test Successful!";
		
	}
	
	@GetMapping("TEST2")
	public String test2() {
		
		System.out.println("Test 2 Successful");
		
		return "Test Successful!";
		
	}
	
	@GetMapping("TEST3")
	public String test3() {
		
		return "Test Successful!";
		
	}
	
}