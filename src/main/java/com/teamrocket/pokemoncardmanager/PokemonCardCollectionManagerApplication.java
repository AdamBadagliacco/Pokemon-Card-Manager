package com.teamrocket.pokemoncardmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.teamrocket.pokemoncardmanager.security.JWTAuthorizationFilter;

@SpringBootApplication
public class PokemonCardCollectionManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonCardCollectionManagerApplication.class, args);
	}
	
	
	//Below is where we determine which pages need authorization to access
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user").permitAll()
				//.antMatchers(HttpMethod.GET, "/TEST").permitAll()
				//Add any additional pages that should be white listed to not need credentials
				//
				.anyRequest().authenticated();
		}
	}

}
