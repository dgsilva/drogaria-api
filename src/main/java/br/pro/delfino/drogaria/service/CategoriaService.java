package br.pro.delfino.drogaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.pro.delfino.drogaria.domain.Categoria;
import br.pro.delfino.drogaria.repository.CategoriaReposiotry;

@Service
public class CategoriaService {

	@Autowired
	CategoriaReposiotry categoriaReposiotry;
	
	 public Categoria buscar(@PathVariable Short codigo) {
	    	Optional<Categoria> resultado = categoriaReposiotry.findById(codigo);
	    	Categoria categoria = resultado.get();
	    	return categoria;
	    	
	    }
}
