package com.teamrocket.pokemoncardmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.teamrocket.pokemoncardmanager.security.JWTAuthorizationFilter;

@SpringBootApplication
public class PokemonCardCollectionManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonCardCollectionManagerApplication.class, args);
	}

	// Below is where we determine which pages need authorization to access

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());//added to fix CORS error

			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
					.antMatchers(HttpMethod.POST, "/signUp").permitAll()
					// Add any additional pages that should be white listed to not need credentials should be added here
					.anyRequest().authenticated();
		}
	}

}
