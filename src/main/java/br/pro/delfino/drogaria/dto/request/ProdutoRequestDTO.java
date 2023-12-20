package br.pro.delfino.drogaria.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.pro.delfino.drogaria.domain.Categoria;
import lombok.Data;

@Data
public class ProdutoRequestDTO {
	
	private Integer codigo;
	@NotBlank(message = "O campo deve ser preenchido")
	private String nome;
	@NotBlank(message = "O campo deve ser preenchido")
	private Byte quantidade;
	@NotBlank(message = "O campo deve ser preenchido")
	private BigDecimal preco;
	private LocalDate dataDeValidade;
    private Categoria categoria;
    

}
