package br.com.mgdev.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1")
public class TestController {
	
	
	
	@GetMapping("/test")
	public String verificaDisponibilidade() {
		
		return "Este EndPoint esta disponível para acesso";
	}
}
