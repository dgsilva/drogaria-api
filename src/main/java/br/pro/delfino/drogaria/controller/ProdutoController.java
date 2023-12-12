package br.pro.delfino.drogaria.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.pro.delfino.drogaria.domain.Produto;
import br.pro.delfino.drogaria.dto.request.ProdutoRequestDTO;
import br.pro.delfino.drogaria.repository.CategoriaReposiotry;
import br.pro.delfino.drogaria.repository.ProdutoRepository;
import br.pro.delfino.drogaria.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
@Tag(name = "Api Rest Produto")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	ProdutoService produtoService;
	@Autowired
	CategoriaReposiotry categoriaRepository;

	@CrossOrigin(origins = "*")
	@GetMapping
	public List<Produto> findAll() {
		return produtoService.listar();
	}

	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Produto create(@RequestBody ProdutoRequestDTO dto) {
		return produtoService.create(dto);
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/{produtoId}")
	public ResponseEntity<Produto> atualizar(@PathVariable Integer produtoId, @RequestBody ProdutoRequestDTO dto) {
		return produtoService.atualizar(produtoId, dto);
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/{codigo}")
	public Produto excluir(@PathVariable Integer codigo) {
		return produtoService.excluir(codigo);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{codigo}") 
	public Produto buscar(@PathVariable Integer codigo) {
		return produtoService.buscar(codigo);

	}
}
