package br.pro.delfino.drogaria.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.pro.delfino.drogaria.domain.Categoria;
import br.pro.delfino.drogaria.repository.CategoriaReposiotry;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("categorias")
public class CategoriaController {
    @Autowired
    private CategoriaReposiotry categoriaReposiotry;
    
    @RequestMapping(value= "/categorias/**", method=RequestMethod.OPTIONS)
	public void corsHeaders(HttpServletResponse response) {
	    response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
	    response.addHeader("Access-Control-Max-Age", "3600");
	}
    
	//Metodo de buscar a listar
    @GetMapping
	public List<Categoria> listar(){
	List< Categoria>categorias =categoriaReposiotry.findAll();
	return categorias;
	}
    //Metodo de inserir
    @CrossOrigin(origins = "*")
    @PostMapping
    public Categoria inserir(@RequestBody Categoria categoria) {
    	Categoria CategoriaSalvar= categoriaReposiotry.save(categoria);
    	return CategoriaSalvar;
    }
    //Metodo de deletar
    @DeleteMapping("/{codigo}")
    public Categoria excluir( @PathVariable Short codigo) {
    	Optional<Categoria>categoria =categoriaReposiotry.findById(codigo);
    	categoriaReposiotry.delete(categoria.get());
    	Categoria  categoriaRetorno = categoria.get();
    	return categoriaRetorno;
    	}
    
  //Metodo de Editar
    @PutMapping
    public Categoria editar(@RequestBody Categoria categoria) {
    	Categoria categoriaEditada = categoriaReposiotry.save(categoria);
    	return categoriaEditada;
    }
    //Bsucar um produto
    @GetMapping("/{codigo}")
    //@PathVariable - > se conecta com a GetMapping e o codigo
    public Categoria buscar(@PathVariable Short codigo) {
    	Optional<Categoria> resultado = categoriaReposiotry.findById(codigo);
    	Categoria categoria = resultado.get();
    	return categoria;
    	
    }
	
}
