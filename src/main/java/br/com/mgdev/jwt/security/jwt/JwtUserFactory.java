package br.com.mgdev.jwt.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.mgdev.jwt.security.entity.User;


public class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(User user) {

		return new JwtUser(user.getUsername(), user.getPassword(),
				mapToGranteAuthorities(user.getIdRoles().getDescription()));
	}

	private static List<GrantedAuthority> mapToGranteAuthorities(String profileGroup) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(profileGroup));
		return authorities;
	}

}
