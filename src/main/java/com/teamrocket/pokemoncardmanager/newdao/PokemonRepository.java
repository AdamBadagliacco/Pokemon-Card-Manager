package com.teamrocket.pokemoncardmanager.newdao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.teamrocket.pokemoncardmanager.entities.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>, JpaSpecificationExecutor {
	
	
}
