package br.pro.delfino.drogaria.dto.request;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UsuarioRequestDTO {

	@NotBlank(message = "O campo deve ser preenchido")
	private String nome;
	@NotBlank(message = "O campo deve ser preenchido")
	@Email(message = "Informe um endereço de email válido.")
	private String email;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,}$",
			message = "Informe a senha com letras maiúsculas minúsculas, números, símbolos e pelo menos 8 caracteres.")
	@NotBlank(message = "Informe a senha do usuário")
	private String senha;
	private Date dataCriacao;
}
