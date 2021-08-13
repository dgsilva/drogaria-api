//package br.pro.delfino.drogaria.repository;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import br.pro.delfino.drogaria.domain.Produto;
//@SpringBootTest
//public class ProdutoRepositoryTest {
//	@Autowired //Ele gerencia os objeto.Ele injecta os objeto
//	private ProdutoRepository produtoRepository;
//   @Test
//	public void inserir() {
//	   Produto  p1 = new Produto(
//			null,
//			    "Fanta Laranja",
//				Byte.valueOf("15"),
//				BigDecimal.valueOf(7.00),
//				LocalDate.of(2021, 11, 04),
//				null
//				);
//	   
//		Produto p2 = new Produto(
//		null,
//		"Skol",
//		Byte.valueOf("25"),
//		BigDecimal.valueOf(12.00),
//		LocalDate.of(2021,4,01),
//		null
//		);
//		
//   
//   
//   Produto p3 = new Produto(
//			null,
//			"Coca-Cola",
//			Byte.valueOf("25"),
//			BigDecimal.valueOf(10.50),
//			LocalDate.of(2021,4,01),
//			null
//			);
//			produtoRepository.save(p1);
//			produtoRepository.save(p2);
//			produtoRepository.save(p3);
//	   }
//
//}
