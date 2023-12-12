package br.pro.delfino.drogaria.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.pro.delfino.drogaria.domain.Produto;
import br.pro.delfino.drogaria.dto.request.ProdutoRequestDTO;
import br.pro.delfino.drogaria.repository.ProdutoRepository;

@Transactional
@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ModelMapper modelMapper;

	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	public Produto create(@RequestBody ProdutoRequestDTO dto) {
		Produto produto = modelMapper.map(dto, Produto.class);
		return produtoRepository.save(produto);
	}

	public ResponseEntity<Produto> atualizar(Integer produtoId, ProdutoRequestDTO dto) {
		if (!produtoRepository.existsById(produtoId)) {
			return ResponseEntity.notFound().build();
		}
		Produto produto = modelMapper.map(dto, Produto.class);
		produto.setCodigo(produtoId);
		produto = produtoRepository.save(produto);

		return ResponseEntity.ok(produto);
	}

	public Produto excluir(Integer codigo) {
		Optional<Produto> produtoexcluir = produtoRepository.findById(codigo);
		produtoRepository.delete(produtoexcluir.get());
		return produtoexcluir.get();
	}
	
	public Produto buscar(@PathVariable Integer codigo) {
    	Optional<Produto> resultado = produtoRepository.findById(codigo);
    	Produto produto = resultado.get();
    	return produto;
    	
    }
}
