package br.com.mgdev.jwt.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mgdev.jwt.security.entity.Roles;
import br.com.mgdev.jwt.security.entity.User;
import br.com.mgdev.jwt.security.jwt.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	
	
	/*
	 * Este metodo deverá ser editado conforme necessidade do desenvolvedor
	 * A partir dele, deveremos efetuar uma chamada em banco de dados
	 * de forma a validar o usuário que nos foi passado pelo login. A partir desse momento, deveremos
	 * obter de forma automática as Roles que este usuário tem acesso, podendo ser uma ou mais roles
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		user.setUsername(username);
		user.setPassword("XYZ123");
		

		if (username.equals("admin")) {
			Roles role = new Roles();
			role.setDescription("ROLE_ADMIN");
			role.setIdRole(1L);
			user.setIdRoles(role);
			
		} else {
			Roles role2 = new Roles();
			role2.setDescription("ROLE_MASTER");
			role2.setIdRole(1L);	
			user.setIdRoles(role2);
		}
		
		
		
		

		return JwtUserFactory.create(user);
		
		//throw new UsernameNotFoundException(String.format("Nenhum usuário encontorado com este e-mail:'%s'", username));

	}

}
