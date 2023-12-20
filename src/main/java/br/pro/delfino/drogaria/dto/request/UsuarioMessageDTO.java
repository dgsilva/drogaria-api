package br.pro.delfino.drogaria.dto.request;

import lombok.Data;

@Data
public class UsuarioMessageDTO {

	private String emailTo;
	private String subject;
	private String body;
}
