package com.service.pet.service.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author Maitrik PANCHAL
 * Token validator to validate jwt data 
 */
@Component
public class JwtTokenValidatorUtil implements Serializable {

	private static final long serialVersionUID = -3440800979295183464L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret:'hcl_secret_key'}")
	private String secret;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// validate token
	public Boolean validateToken(String token, String userName) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userName) && !isTokenExpired(token));
	}

}
