package br.pro.delfino.drogaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.pro.delfino.drogaria.domain.Categoria;

public interface CategoriaReposiotry extends JpaRepository<Categoria, Short> {
	
	@Query("SELECT c from Categoria c  WHERE c.nome = :nome")
	Categoria find(@Param("nome")String nome);
}
