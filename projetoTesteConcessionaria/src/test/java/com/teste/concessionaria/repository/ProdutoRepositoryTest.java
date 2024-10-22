package com.teste.concessionaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.teste.concessionaria.entity.Produto;

	@DataJpaTest
	class ProdutoRepositoryTest {
		
		@Autowired
		private ProdutoRepository produtoRepository;
		
		@DisplayName("Testando o Save")
	    @Test
		void testSalvarRepository() {
			
			//Given / Arrange
			Produto produto1 =new Produto(null,"Celular",
					                            15.000);
			//When / Act
			Produto saveProduto = produtoRepository.save(produto1);
			
			//Then /Assert
			assertNotNull(saveProduto);
			assertTrue(saveProduto.getId()>0);
			
		}
		@DisplayName("Testando GET para todos Produto")
		@Test
		void testGetAllRepository() {
			
			// Given / Arrange
			Produto produto1 =new Produto(null, "Celular",
					15.000);
			
			Produto produto2 =new Produto(null,"Notebook",
					8.000);
			
			produtoRepository.save(produto1);
			produtoRepository.save(produto2);
			
			//When /Act
			List<Produto> produtoList = produtoRepository.findAll();
			
			//Then / Assert
			assertNotNull(produtoList);
			assertEquals(2, produtoList.size());
			
		}
		
		@DisplayName("Testando GET ById")
		@Test
		void testGetById() {
			
			// Given / Arrange
			Produto produto1 =new Produto(null,"Celular",
					15.000);
			
			
			produtoRepository.save(produto1);
			
			//When /Act
			Produto saveProduto = produtoRepository.findById(produto1.getId()).get();
			
			//Then / Assert
			assertNotNull(saveProduto);
			assertEquals(produto1.getId(), saveProduto.getId());
			

	}
		@DisplayName("Testando Update")
		@Test
		void testUpdateProduto() {
			
			// Given / Arrange
			Produto produto1 =new Produto(null,"Mouse",
					86.90);
			
			
			produtoRepository.save(produto1);
			
			//When /Act
			Produto saveProduto = produtoRepository.findById(produto1.getId()).get();
			produto1.setNome("Webcan");
			produto1.setPreco(86.90);
			
			Produto updateProduto = produtoRepository.save(saveProduto);
			
			//Then / Assert
			assertNotNull(updateProduto);
			assertEquals("Webcan",updateProduto.getNome());
			assertEquals(86.90,updateProduto.getPreco());
	   }
		
		@DisplayName("Testando o Delete")
		@Test
		void testDeleteProduto() {
			
			// Given / Arrange
			Produto produto1 =new Produto(null,"Carregador",
					10.00);
			
			
			produtoRepository.save(produto1);
			
			//When /Act
			produtoRepository.deleteById(produto1.getId());
			
			Optional<Produto> produtoOptional = produtoRepository.findById(produto1.getId());
			
			//Then / Assert
			assertTrue(produtoOptional.isEmpty());
		}
	}

