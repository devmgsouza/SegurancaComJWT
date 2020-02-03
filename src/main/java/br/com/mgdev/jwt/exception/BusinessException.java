package br.com.mgdev.jwt.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final ErrorCode code;
	private final String mensagemUsuario;
	
	public BusinessException(ErrorCode code) {
		super();
		this.mensagemUsuario = "";
		this.code = code;
	}
	
	
	public BusinessException(ErrorCode code, String mensagemUsuario) {
		super();
		this.code = code;
		this.mensagemUsuario = mensagemUsuario;
	}
	
	
	
	
	
	
}
