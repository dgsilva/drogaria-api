package br.pro.delfino.drogaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.pro.delfino.drogaria.domain.Produto;
import br.pro.delfino.drogaria.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public Produto create(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto update(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public List<Produto> listar(){
		List<Produto> resultado = produtoRepository.findAll();
		return resultado;
	}
}
