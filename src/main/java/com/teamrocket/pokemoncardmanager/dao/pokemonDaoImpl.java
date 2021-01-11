package com.teamrocket.pokemoncardmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.teamrocket.pokemoncardmanager.entities.Pokemon;
import com.teamrocket.pokemoncardmanager.entities.User;

public class pokemonDaoImpl implements pokemonDao{

	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public Pokemon getPokemonById(int id) {
		// TODO Auto-generated method stub
		try {
			final String SELECT_POKEMON_BY_ID="SELECT * FROM POKEMON WHERE id = ?";
			return jdbc.queryForObject(SELECT_POKEMON_BY_ID, new PokemonMapper(), id);
			
		} catch (DataAccessException ex) {
			return null;
		}
	}

	@Override
	public List<Pokemon> getAllPokemons() {
		// TODO Auto-generated method stub
		final String SELECT_ALL_POKEMON = "SELECT * FROM pokemon";
		List<Pokemon> allPokemons = jdbc.query(SELECT_ALL_POKEMON, new PokemonMapper());
		return allPokemons;
	}

	@Override
	@Transactional
	public Pokemon addPokemon(Pokemon pokemon) {
		// TODO Auto-generated method stub
		final String INSERT_POKEMON = "INSERT INTO pokemon(id,name,pokedexNumber,imgUrl)"
		+"(?,?,?,?)";
		jdbc.update(INSERT_POKEMON,
				pokemon.getId(),
				pokemon.getName(),
				pokemon.getPokedexNumber(),
				pokemon.getImgUrl());
		return pokemon;
	}

	@Override
	public void updatePokemon(Pokemon pokemon) {
		// TODO Auto-generated method stub
		final String UPDATE_POKEMON= "UPDATE pokemon name = ?, pokedexNumber=?,imgUrl=?"
		+"WHERE id=?";
		jdbc.update(UPDATE_POKEMON,
				pokemon.getName(),
				pokemon.getPokedexNumber(),
				pokemon.getImgUrl(),
				pokemon.getId());		
	}

	@Override
	@Transactional
	public void removePokemon(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pokemon> getCollection(User user) {
		// TODO Auto-generated method stub
		
		
		return null;
	}
	
	public List<Integer> getPokemonIdsFromCollection(){
		final String SELECT_COLLECTION = "SELECT * FROM collection c " + "WHERE c.user_id=?";
		List<Integer> pokemonIds = jdbc.queryForList(SELECT_COLLECTION);
	}

	public static final class PokemonMapper implements RowMapper<Pokemon>{

		@Override
		public Pokemon mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Pokemon pokemon = new Pokemon();
			pokemon.setId(rs.getString("id"));
			pokemon.setName(rs.getString("name"));
			pokemon.setImgUrl(rs.getString("imgUrl"));
			pokemon.setPokedexNumber(rs.getString("pokedexNumber"));
			
			return null;
		}
		
	}
}
