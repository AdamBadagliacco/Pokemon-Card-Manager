package com.teamrocket.pokemoncardmanager.dao;

import java.util.List;

import com.teamrocket.pokemoncardmanager.entities.User;

public interface userDao {
	User getUserById(int id);
	User addUser(User user);
	void updateUser(User user);
	void removeUser(int id);
	List<User> getAllUsers();
}
