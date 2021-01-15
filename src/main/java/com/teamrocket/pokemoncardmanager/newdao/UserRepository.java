package com.teamrocket.pokemoncardmanager.newdao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.teamrocket.pokemoncardmanager.entities.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {
	
	
}

