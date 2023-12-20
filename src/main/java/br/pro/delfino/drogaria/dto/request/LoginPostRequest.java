package br.pro.delfino.drogaria.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginPostRequest {
	
	@NotBlank(message = "O campo deve ser preenchido")
	private String email;
	@NotBlank(message = "O campo deve ser preenchido")
	private String senha;
	

}
