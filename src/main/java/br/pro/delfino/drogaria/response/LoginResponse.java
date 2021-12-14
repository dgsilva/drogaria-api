package br.pro.delfino.drogaria.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
	
	private Integer statusCode;
	private String mensagem;
	private String acessToken;
	private String nomeUsuario;
	private String emailUsuario;

}
