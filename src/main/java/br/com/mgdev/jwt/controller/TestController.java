package br.com.mgdev.jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1")
public class TestController {
	
	
	
	@GetMapping("/test")
	public String verificaDisponibilidade() {
		
		return "Este EndPoint nao esta disponível para acesso";
	}
	
	@GetMapping("/ambos")
	@PreAuthorize("hasAnyRole('MASTER, ADMIN')")
	public String acessoAmbos() {
		
		return "Este EndPoint esta disponível para acesso MASTER e ADMIN";
	} 
	
	@GetMapping("/master")
	@PreAuthorize("hasAnyRole('MASTER')")
	public String acessoMaster() {
		
		return "Este EndPoint esta disponível para acesso MASTER";
	} 
	
	@GetMapping("/admin")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String acessoAdmin() {
		
		return "Este EndPoint esta disponível para acesso ADMIN";
	} 
}
