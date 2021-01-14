package com.teamrocket.pokemoncardmanager.dao;

import java.util.List;

import com.teamrocket.pokemoncardmanager.entities.Pokemon;
import com.teamrocket.pokemoncardmanager.entities.User;


public interface pokemonDao {
    Pokemon getPokemonById(String id);
    List<Pokemon> getAllPokemons();
    Pokemon addPokemon(Pokemon pokemon);
    void updatePokemon(Pokemon pokemon);
    void removePokemon(String id);
    
    List<Pokemon> getCollection(int userId);
}
