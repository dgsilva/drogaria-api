package br.pro.delfino.drogaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.delfino.drogaria.repository.CategoriaReposiotry;

@Service
public class CategoriaService {

	@Autowired
	CategoriaReposiotry categoriaReposiotry;
}
