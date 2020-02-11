package br.com.mgdev.jwt.security.jwt;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class JwtCustomAuthenticationProvider implements AuthenticationProvider {

 	
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
  
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
     
      
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}