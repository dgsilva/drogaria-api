package br.pro.delfino.drogaria.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import br.pro.delfino.drogaria.api.exceptionhandler.RecursoNaoEncontradoException;
import br.pro.delfino.drogaria.domain.Categoria;
import br.pro.delfino.drogaria.dto.request.CategoriaRequestDTO;
import br.pro.delfino.drogaria.repository.CategoriaReposiotry;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaReposiotry categoriaReposiotry;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<Categoria> buscar(Short codigo) {
		Optional<Categoria> categoria = categoriaReposiotry.findById(codigo);

		if (categoria.isPresent()) {
			return ResponseEntity.ok(categoria.get());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
	}

	public List<Categoria> listar() {
		List<Categoria> categoriaListas = categoriaReposiotry.findAll();
		return categoriaListas;
	}

	public List<Categoria> listarTodos() {
		try {
			List<Categoria> categorias = listar();
			return categorias;
		} catch (RecursoNaoEncontradoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada", e);
		}
	}

	public Categoria salvar(CategoriaRequestDTO dto) {
		if (categoriaReposiotry.find(dto.getNome()) != null) {
			throw new IllegalArgumentException("Essa categoria já existe cadastro no banco de dados.");
		}
		if (dto.getNome() == null) {
			throw new IllegalArgumentException("Categoria não encontrada");
		}
		Categoria categoria = modelMapper.map(dto, Categoria.class);
		return categoriaReposiotry.save(categoria);
	}

	public ResponseEntity<Categoria> atualizar(Short categoriaId, CategoriaRequestDTO dto) {
		if (!categoriaReposiotry.existsById(categoriaId)) {
			return ResponseEntity.notFound().build();
		}

		Categoria categoria = modelMapper.map(dto, Categoria.class);
		categoria.setCodigo(categoriaId);
		categoria = categoriaReposiotry.save(categoria);
		return ResponseEntity.ok(categoria);
	}

	public Categoria excluir(@PathVariable Short codigo) {
		Optional<Categoria> categoria = categoriaReposiotry.findById(codigo);
		categoriaReposiotry.delete(categoria.get());
		Categoria categoriaRetorno = categoria.get();
		return categoriaRetorno;
	}

}
