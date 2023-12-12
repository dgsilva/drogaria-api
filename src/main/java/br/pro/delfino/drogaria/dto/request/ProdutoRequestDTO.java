package br.pro.delfino.drogaria.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;


import br.pro.delfino.drogaria.domain.Categoria;
import lombok.Data;

@Data
public class ProdutoRequestDTO {
	
	private Integer codigo;
	private String nome;
	private Byte quantidade;
	private BigDecimal preco;
	private LocalDate dataDeValidade;
    private Categoria categoria;
    

}
