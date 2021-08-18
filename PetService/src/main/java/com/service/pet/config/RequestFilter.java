package com.service.pet.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.service.pet.service.UserDetailsService;
import com.service.pet.service.jwt.JwtTokenValidatorUtil;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * @author Maitrik PANCHAL
 * Request filter to filter each incoming request for valid authentication and authorization.
 */
@Component
public class RequestFilter extends OncePerRequestFilter {

	private static final Logger _log = LogManager.getLogger(RequestFilter.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenValidatorUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				_log.info("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				_log.info("JWT Token has expired");
			} catch (Exception e) {
				_log.info("JWT Token signature has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (jwtTokenUtil.validateToken(jwtToken, userDetails.getUsername())) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetails,userDetails.getPassword());
//				usernamePasswordAuthenticationToken
//						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}
}
