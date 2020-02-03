package br.com.mgdev.jwt.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	
	ACCESS_DENIED_USER_PASSWORD_INVALID(1000, "Acesso negado. Nome de usuário ou senha inválidos."),
	ACCESS_DENIED(1000, "Acesso negado.");
	
	private final int code;
	private final String message;
	
	ErrorCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
	
	@Override
    public String toString() {
        return String.valueOf(this.message + " - " + this.code);
    }

	

}
