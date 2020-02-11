package br.com.mgdev.jwt.security.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	

	private Long idUser;
	
	private String username;
	
	private String password;

	private Roles idRoles;



}
