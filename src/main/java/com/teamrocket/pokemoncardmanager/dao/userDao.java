package com.teamrocket.pokemoncardmanager.dao;

import com.teamrocket.pokemoncardmanager.entities.User;

public interface userDao {
	User getUserById(int id);
	User addUser(User user);
	void updateUser(User user);
	void removeUser(int id);
}
