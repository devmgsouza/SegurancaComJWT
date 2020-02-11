package br.com.mgdev.jwt.security.jwt;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import br.com.mgdev.jwt.exception.BusinessException;
import br.com.mgdev.jwt.exception.ErrorCode;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2538394862633273751L;

	/*
	 * Metodo que será executado caso a authenticação do usuário seja falha ou o token seja inválido
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException, BusinessException {
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorCode.ACCESS_DENIED.toString());
		
	}

}
