package br.pro.delfino.drogaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.pro.delfino.drogaria.domain.Produto;
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
     
}
