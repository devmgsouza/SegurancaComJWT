package br.com.mgdev.jwt.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mgdev.jwt.security.entity.UserAuthentication;
import br.com.mgdev.jwt.security.jwt.JwtCustomAuthenticationProvider;
import br.com.mgdev.jwt.security.jwt.JwtTokenUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private JwtCustomAuthenticationProvider jwtCustomAuthenticationProvider;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	/*
	 * 
	 * Metodo Responsável por receber o usuário e senha
	 *  e fazer as chamadas para gerar o token de authenticação
	 *  
	 */
	@PostMapping
	public String getToken(@RequestBody UserAuthentication userAuthentication) {
	

		
		final Authentication authentication = jwtCustomAuthenticationProvider.authenticate(
					new UsernamePasswordAuthenticationToken(
							userAuthentication.getUsername(),
							userAuthentication.getPassword(),
							Collections.emptyList()
							)
					);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);

			final UserDetails userDetails = userDetailsService.loadUserByUsername(userAuthentication.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			

			
		return token;
	}

}

