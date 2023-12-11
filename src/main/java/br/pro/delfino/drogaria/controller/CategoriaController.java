package br.pro.delfino.drogaria.controller;

import java.util.List;

import java.util.Optional;

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
import org.springframework.web.server.ResponseStatusException;

import br.pro.delfino.drogaria.api.exceptionhandler.RecursoNaoEncontradoException;
import br.pro.delfino.drogaria.domain.Categoria;
import br.pro.delfino.drogaria.repository.CategoriaReposiotry;
import br.pro.delfino.drogaria.service.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categorias")
@Tag(name = "Api Rest Categoria")
public class CategoriaController {
	
    @Autowired
    private CategoriaReposiotry categoriaReposiotry;
    
    @Autowired
    private CategoriaService categoriaService;
    
	//Metodo de buscar a listar
    @CrossOrigin(origins = "*")
    @GetMapping()
	public List<Categoria> listar(){
    	try {
    		List< Categoria>categorias = categoriaService.listar();
    		return categorias;
    	}catch (RecursoNaoEncontradoException e) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada", e);
		}
	}
    //Metodo de inserir
    @CrossOrigin(origins = "*")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Categoria inserir(@Valid @RequestBody Categoria categoria) {
    	Categoria CategoriaSalvar= categoriaService.salvar(categoria);
    	return CategoriaSalvar;
    }
    //Metodo de deletar
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{codigo}")
    public Categoria excluir( @PathVariable Short codigo) {
    	Optional<Categoria>categoria =categoriaReposiotry.findById(codigo);
    	categoriaReposiotry.delete(categoria.get());
    	Categoria  categoriaRetorno = categoria.get();
    	return categoriaRetorno;
    	}

    @CrossOrigin(origins = "*")
    @PutMapping("/{categoriaId}")
    public ResponseEntity<Categoria> atualizar(@Valid @PathVariable Short categoriaId, @RequestBody Categoria categoria) {
      if(!categoriaReposiotry.existsById(categoriaId)) {
    	  return ResponseEntity.notFound().build();
      }
        categoria.setCodigo(categoriaId);
    	categoria = categoriaReposiotry.save(categoria);	
    	
    	return ResponseEntity.ok(categoria);
    }
    //Bsucar um produto
    @CrossOrigin(origins = "*")
    @GetMapping("/{codigo}")    //@PathVariable - > se conecta com a GetMapping e o codigo
    public Categoria buscar(@PathVariable Short codigo) {
    	try {
    	Categoria categoria = categoriaService.buscarPorCodigo(codigo);
    	return categoria;
    	}catch (RecursoNaoEncontradoException e) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada", e);
		}
    }
	
}
