package br.pro.delfino.drogaria.controller;

import java.util.List;
import javax.validation.Valid;
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
import br.pro.delfino.drogaria.domain.Categoria;
import br.pro.delfino.drogaria.dto.CategoriaRequestDTO;
import br.pro.delfino.drogaria.service.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categorias")
@Tag(name = "Api Rest Categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	// Metodo de buscar a listar
	@CrossOrigin(origins = "*")
	@GetMapping()
	public List<Categoria> listar() {
		return categoriaService.listarTodos();
	}

	// Metodo de inserir
	@CrossOrigin(origins = "*")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public Categoria inserir(@Valid @RequestBody CategoriaRequestDTO dto) {
		return categoriaService.salvar(dto);
	}

	// Metodo de deletar
	@CrossOrigin(origins = "*")
	@DeleteMapping("/{codigo}")
	public Categoria excluir(@PathVariable Short codigo) {
		return categoriaService.excluir(codigo);
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/{categoriaId}")
	public ResponseEntity<Categoria> atualizar(@Valid @PathVariable Short categoriaId,
			@RequestBody CategoriaRequestDTO dto) {
		return categoriaService.atualizar(categoriaId, dto);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscar(@PathVariable Short codigo) {
		return categoriaService.buscar(codigo);
	}
}
