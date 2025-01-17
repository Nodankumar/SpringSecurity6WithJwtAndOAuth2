package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springsecurity.filters.JWTFilter;
import com.springsecurity.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
	
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JWTFilter JwtFilter;


    @Bean
    SecurityFilterChain securityFileChain(HttpSecurity http) throws Exception {
		
    	return http.csrf(csrfcustomizer -> csrfcustomizer.disable())
    				.authorizeHttpRequests(request -> request.requestMatchers("/register/**", "/login/**").permitAll().anyRequest().authenticated())
    				.httpBasic(Customizer.withDefaults())
    				//.formLogin(Customizer.withDefaults())
    				.sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    				.addFilterBefore(JwtFilter,UsernamePasswordAuthenticationFilter.class)
    				//disable frame-option only if locahost refuses to connect h2-console(just for development))
    				.headers(headers -> headers
    		                .frameOptions().disable()
    			            )
    				.build(); 
	}
   
    /* 
    @Bean
    UserDetailsService userDetailsService() {
    	
    	UserDetails grogu = User.withDefaultPasswordEncoder()
    									.username("Grogu")
    									.password("Grogu123")
    									.roles("USER").build();
    	UserDetails gojo = User.withDefaultPasswordEncoder()
								.username("Gojo")
								.password("Gojo123")
								.roles("USER").build();
    	System.err.println("gojo.getPassword(): "+gojo.getPassword());
    	
		return new InMemoryUserDetailsManager(grogu, gojo);
    	
    }*/
    
    @Bean
    AuthenticationProvider authenticationProvider() {
    	
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    	provider.setUserDetailsService(userDetailsService);
    	
    	return provider;
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
    	
    }

}
