package com.teamrocket.pokemoncardmanager;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.teamrocket.pokemoncardmanager.dao.pokemonDao;
import com.teamrocket.pokemoncardmanager.dao.userDao;
import com.teamrocket.pokemoncardmanager.entities.Pokemon;
import com.teamrocket.pokemoncardmanager.entities.User;

@SpringBootTest
public class UserDaoTest {
	@Autowired 
	userDao userDao;
	@Autowired
	pokemonDao pokemonDao;
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    @BeforeEach
    public void setUp() {
    	List<User> users = userDao.getAllUsers();
    	for(User user : users) {
    		userDao.removeUser(user.getId());
    	}
    	List<Pokemon> pokemons = pokemonDao.getAllPokemons();
    	for(Pokemon pokemon : pokemons) {
    		pokemonDao.removePokemon(pokemon.getId());
    	}
    }
    @AfterEach
    public void tearDown() {
    	
    }
    @Test
    public void testGetAllUsers() {
    	
    }
}
 