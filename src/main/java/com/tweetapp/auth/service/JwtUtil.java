package com.tweetapp.auth.service;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil{
	
	/**
	 * 
	 */
	
	private String secretKey = "${jwt.secret}";
	//byte[] decodeSecret = Base64.getDecoder().decode(secretKey);
	
	public String  extractUsername(String token)
	{
		return extractClaim(token, Claims::getSubject);
	}
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
	
	public String generateToken(UserDetails  userDetails)
	{
		
		Map<String, Object> claims = new HashMap<>();
		
		String token = Jwts.builder()
				       .setClaims(claims)
				       .setSubject(userDetails.getUsername())
				       .setIssuedAt(new Date(System.currentTimeMillis()))
				       .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30 ))
				       .signWith(SignatureAlgorithm.HS512, secretKey)
				       .compact();
		return token;
	}
	public Boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			return true;
		}
		catch(Exception e) {
			return false;
		}
		//return !isTokenExpired(token);
	}

}
