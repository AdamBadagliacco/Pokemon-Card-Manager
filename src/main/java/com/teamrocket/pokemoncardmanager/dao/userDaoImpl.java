package com.teamrocket.pokemoncardmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamrocket.pokemoncardmanager.entities.User;

@Repository
public class userDaoImpl implements userDao {
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public User getUserById(int id) {
		try {
			final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
			return jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), id);
		} catch (DataAccessException ex) {
			return null;
		}
	}

	@Override
	@Transactional
	public User addUser(User user) {
		final String INSERT_USER = "INSERT INTO user(name, password) " + "VALUES(?,?)";
		jdbc.update(INSERT_USER, user.getName(), user.getPassword());

		int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
		user.setId(newId);
		return user;
	}

	@Override
	public void updateUser(User user) {
		final String UPDATE_USER = "UPDATE user SET name = ?, password = ?" + "WHERE id = ?";
		jdbc.update(UPDATE_USER, user.getName(), user.getPassword(), user.getId());

	}

	@Override
	@Transactional
	public void removeUser(int id) {
		// TODO Auto-generated method stub
		final String DELETE_COLLECTION = "DELETE FROM collection WHERE user_id = ?";
		jdbc.update(DELETE_COLLECTION, id);

		final String DELETE_USER = "DELETE FROM user WHERE id = ?";
		jdbc.update(DELETE_USER, id);
	}
	@Override
	public List<User> getAllUsers() {
       final String SELECT_ALL_USERS = "SELECT * FROM user";
       List<User> users = jdbc.query(SELECT_ALL_USERS, new UserMapper());
       return users;
	}
	public static final class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	}


}
