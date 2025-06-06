package com.daniel.brigadeiro.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.daniel.brigadeiro.security.JWTAuthenticationFilter;
import com.daniel.brigadeiro.security.JWTAuthorizationFilter;
import com.daniel.brigadeiro.security.JWTUtil;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
	private UserDetailsService detailsService;

    @Bean
    SecurityFilterChain sucurityFilterChain(HttpSecurity http) throws Exception {
				return http
				.csrf(csrf -> csrf.disable())
                .addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(),jwtUtil))
                .addFilter(new JWTAuthorizationFilter(authenticationConfiguration.getAuthenticationManager(),jwtUtil,detailsService))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authoriza -> authoriza
                		.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                		.requestMatchers("/ws-rank/**").permitAll() // Permite acesso à rota WebSocket
                .anyRequest().authenticated())
				.build();
    }
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(detailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	
	  @Bean
	  WebSecurityCustomizer webSecurityCustomizer() {
	        return web -> web.ignoring().requestMatchers(
	                new AntPathRequestMatcher("/h2-console/**")
	        );
	    }
	 
	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
	        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));  // Inclua outras origens necessárias
	        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
	        corsConfiguration.setAllowCredentials(true);
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", corsConfiguration);
	        return source;
	    }
	 
	    @Bean
	     BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
