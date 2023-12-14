package br.pro.delfino.drogaria.dto.request;

import java.util.Date;

import lombok.Data;

@Data
public class UsuarioRequestDTO {

	private String nome;
	private String email;
	private String senha;
	private Date dataCriacao;
}
