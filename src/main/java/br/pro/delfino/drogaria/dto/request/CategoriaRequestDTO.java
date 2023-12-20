package br.pro.delfino.drogaria.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CategoriaRequestDTO {
	
		private Short codigo;
		@NotBlank(message = "O campo deve ser preenchido")
	    private String nome;

}
