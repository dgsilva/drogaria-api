package br.pro.delfino.drogaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pro.delfino.drogaria.domain.Categoria;

@Repository
public interface CategoriaReposiotry extends JpaRepository<Categoria, Short> {

}
