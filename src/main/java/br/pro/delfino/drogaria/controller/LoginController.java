package br.pro.delfino.drogaria.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.pro.delfino.drogaria.domain.Usuario;
import br.pro.delfino.drogaria.dto.request.LoginPostRequest;
import br.pro.delfino.drogaria.dto.response.LoginResponse;
import br.pro.delfino.drogaria.service.LoginService;

@CrossOrigin(origins = "*")
@RequestMapping("/login")
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	
	@CrossOrigin
	@PostMapping
	@ResponseBody
	public ResponseEntity<LoginResponse>post(@RequestBody LoginPostRequest request){
		return loginService.acessar(request);
	}
	
	@PostMapping("/create")
	public Usuario create(@RequestBody Usuario usuario) {
		return loginService.cadastrarUsuario(usuario);
	}
	
	
}
	
