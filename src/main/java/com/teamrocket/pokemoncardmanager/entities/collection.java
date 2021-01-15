package com.teamrocket.pokemoncardmanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class collection {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	public String id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "userId", nullable = false, insertable=false, updatable=false)
	private User user;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "pokemonId", nullable = false, insertable=false, updatable=false)
	private Pokemon pokemon;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	
	
	

}
