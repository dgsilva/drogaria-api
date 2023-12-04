 package br.pro.delfino.drogaria.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.pro.delfino.drogaria.api.exceptionhandler.RecursoNaoEncontradoException;
import br.pro.delfino.drogaria.domain.Categoria;
import br.pro.delfino.drogaria.repository.CategoriaReposiotry;

@Service
public class CategoriaService {

	@Autowired
	CategoriaReposiotry categoriaReposiotry;
	
	 public Categoria buscarPorCodigo(@PathVariable Short codigo) {
	    	Optional<Categoria> resultado = categoriaReposiotry.findById(codigo);
	    	
	    	if(Optional.empty() != null) {
	    		throw new RecursoNaoEncontradoException();
	    	}
	    	
	    	Categoria categoria = resultado.get();
	    	return categoria;
	    	
	    }
	 
	 public List<Categoria> listar(){
		 List<Categoria> categoriaListas= categoriaReposiotry.findAll();
		 return categoriaListas;
	 }
	 
	 public Categoria salvar(@Valid @RequestBody Categoria categoria) {
		Categoria CategoriaSalvar= categoriaReposiotry.save(categoria);
	    return CategoriaSalvar;
	 }
}
