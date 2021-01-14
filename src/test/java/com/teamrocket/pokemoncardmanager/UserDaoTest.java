package com.teamrocket.pokemoncardmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testAddAndGetUser() {
        User user = new User();
        user.setName("Test User First");
        user.setPassword("Test User Last");
        user = userDao.addUser(user);
        
        User fromDao = userDao.getUserById(user.getId());
        assertEquals(user, fromDao);
    }
    @Test
    public void testGetAllUsers() {
    	User user = new User();
    	user.setName("John");
    	user.setPassword("pass");
    	user = userDao.addUser(user);
    	User user2 = new User();
    	user2.setName("Jimmy");
    	user2.setPassword("pass2");
    	user2 = userDao.addUser(user2);
    	
    	List<User> users = userDao.getAllUsers();
    	
        assertEquals(2, users.size());
        assertTrue(users.contains(user));
        assertTrue(users.contains(user2));
    }
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("Test User First");
        user.setPassword("Test User Last");
        user = userDao.addUser(user);
        
        User fromDao = userDao.getUserById(user.getId());
        assertEquals(user, fromDao);
        
        user.setName("New Test User First");
        userDao.updateUser(user);
        
        assertNotEquals(user, fromDao);
        
        fromDao = userDao.getUserById(user.getId());
        
        assertEquals(user, fromDao);
    }
    @Test
    public void testRemoveUser() {
    	
    }
}
 