package com.teamrocket.pokemoncardmanager.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.teamrocket.pokemoncardmanager.entities.User;
import com.teamrocket.pokemoncardmanager.newdao.PokemonRepository;
import com.teamrocket.pokemoncardmanager.newdao.UserRepository;
import com.teamrocket.pokemoncardmanager.newdao.collectionRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class NewController {
	
	@Autowired
	collectionRepository collections;
	
	@Autowired
	UserRepository users;
	
	@Autowired
	PokemonRepository pokemons;
	

	@PostMapping("login")
	public String login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		
		System.out.println("User endpoint hit");
		
		
		//Check if User exists (if they do, check if the password is right. If not, return exception the User doesn't have an account)
		List<User> allUsers = users.findAll();
			for(int i = 0; i < allUsers.size(); i++) {
				if(allUsers.get(i).getName().equals(username)) { //User Found!
					
					if(allUsers.get(i).getPassword().equals(pwd)) { //Successful Login
						
						String token = getJWTToken(username);
						return token;
						
					}
					else {
						return "ERROR: Incorrect Password";
					}
					
				}
			}
		
		
		return "ERROR: Username Not Found";
		
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
	
	
	/*
	
	@PostMapping("signUp")
	public String signUp(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		
		System.out.println("Signup endpoint hit");
		
		//Check that the username does not already exist in database, if it does return "username already exists error"
		List<User> allUsers = myUserDao.getAllUsers();
		for(int i = 0; i < allUsers.size(); i++) {
			if(allUsers.get(i).getName().equals(username)) {
				return "ERROR: Selected username already exists"; 
			}
		}
		
		//Create a new user in the database with the correct username password and Token
		String token = getJWTToken(username);
		User user = new User();
		user.setName(username);
		user.setPassword(pwd);
		user.setToken(token);	
		myUserDao.addUser(user);
		
		return token;
		
	}
	
	@PostMapping("getCollection")
	public User getCollection(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		
		System.out.println("Get Collection endpoint hit");
		
		//Check that the username , password, and token against the database, if any are incorrect return an error
		
		//return a Json array of all the cards in the user's collection
		
		String token = getJWTToken(username);
		User user = new User();
		user.setName(username);
		user.setToken(token);		
		return user;
		
	}
	
	@PostMapping("addCard")
	public User addCard(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		
		System.out.println("Add card endpoint hit");
		
		//Check that the username , password, and token against the database, if any are incorrect return an error
		
		//return a Json array of the card in the user's collection
		
		String token = getJWTToken(username);
		User user = new User();
		user.setName(username);
		user.setToken(token);		
		return user;
		
	}
	
	@PostMapping("removeCard")
	public User removeCard(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		
		System.out.println("Remove card endpoint hit");
		
		//Check that the username , password, and token against the database, if any are incorrect return an error
		
		//return a Json array of all the cards in the user's collection
		
		String token = getJWTToken(username);
		User user = new User();
		user.setName(username);
		user.setToken(token);		
		return user;
		
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
	
	*/
	
}