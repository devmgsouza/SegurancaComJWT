package br.com.mgdev.jwt.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private final static String CLAIM_KEY_USERNAME = "sub";
	private final static String CLAIM_KEY_CREATED = "created";
	

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	
	
	public String getUsernameFromToken(String token) {
		
		Claims claims = null;
		claims = getClaimsFromToken(token);
		if (claims != null) {
			return claims.getSubject();
		}
		
		return null;
	}
	
	public Date getExpirationFromToken(String token) {
		
		
			Claims claims = getClaimsFromToken(token);
			if (claims != null) {
				return claims.getExpiration();
			}
			return null;
		
	}
	
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		
		return claims;
	}
	
	private Boolean isTokenExpirated(String token) {
		return getExpirationFromToken(token).before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		final Date createdDate = new Date();
		claims.put(CLAIM_KEY_CREATED, createdDate);
		
		return doGenerateToken(claims);
	}

	private String doGenerateToken(Map<String, Object> claims) {
		final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
		final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpirated(token));
	}
	
	public String refreshToken(String token) {
		String refreshedToken = null;
	
			Claims claims = getClaimsFromToken(token);
			if (claims != null) {
				claims.put(CLAIM_KEY_CREATED, new Date());
				refreshedToken = doGenerateToken(claims);
			}		
		
		
		return refreshedToken;
	}


	public Boolean validateToken(String token, UserDetails userDetails) {
		
		JwtUser user = (JwtUser) userDetails;
		final String username = getUsernameFromToken(token);
		return (username.equals(user.getUsername()) && !isTokenExpirated(token));
		
	}
	
	
	
}
