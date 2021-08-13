package br.pro.delfino.drogaria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pro.delfino.drogaria.domain.Produto;
import br.pro.delfino.drogaria.repository.ProdutoRepository;
import br.pro.delfino.drogaria.service.ProdutoService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("product")
@Api(value= "Api Rest Produto")
@CrossOrigin(origins = "*")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
	public List<Produto> findAll(){
	List<Produto> listProduto = produtoService.listar();
	return listProduto;	
	}
	
	@PostMapping
	public Produto create(@RequestBody Produto produto) {
		Produto salve = produtoService.create(produto);
		return salve;
	}
	
	@PutMapping
	public Produto update(@RequestBody Produto produto) {
		Produto update = produtoService.update(produto);
		return update;
	}
	
	@DeleteMapping
	public Produto excluir(@PathVariable Integer codigo) {
		Optional<Produto>produtoexcluir = produtoRepository.findById(codigo);
		produtoRepository.delete(produtoexcluir.get());
		Produto produtoRetorno = produtoexcluir.get();
		return produtoRetorno;
	}
}
