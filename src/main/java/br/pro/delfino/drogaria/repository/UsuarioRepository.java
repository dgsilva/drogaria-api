package br.pro.delfino.drogaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.pro.delfino.drogaria.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("from Usuario u where u.email = :param")
	Usuario findByEmail(@Param("param") String email);
	
	@Query("from Usuario u where u.email = :param1 and u.senha = :param2 ")
	Usuario findByEmailAndSenha(@Param("param1")String email,@Param("param2") String senha);
}
