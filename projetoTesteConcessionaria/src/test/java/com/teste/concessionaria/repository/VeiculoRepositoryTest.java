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

import com.teste.concessionaria.entity.Veiculo;

@DataJpaTest
class VeiculoRepositoryTest {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@DisplayName("Testando o Save")
	@Test
	void testSalvarRepository() {

		// Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "Fiat", "Uno", 2008, "Vermelho");
		// When / Act
		Veiculo saveVeiculo = veiculoRepository.save(veiculo1);

		// Then /Assert
		assertNotNull(saveVeiculo);
		assertTrue(saveVeiculo.getId() > 0);

	}

	@DisplayName("Testando GET para todos Veiculo")
	@Test
	void testGetAllRepository() {

		// Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "Fiat", "Uno", 2008, "Vermelho");

		Veiculo veiculo2 = new Veiculo(null, "chevrolet", "Onix", 2016, "Branco");

		veiculoRepository.save(veiculo1);
		veiculoRepository.save(veiculo2);

		// When /Act
		List<Veiculo> veiculoList = veiculoRepository.findAll();

		// Then / Assert
		assertNotNull(veiculoList);
		assertEquals(2, veiculoList.size());

	}

	@DisplayName("Testando GET ById")
	@Test
	void testGetById() {

		// Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "Fiat", "Uno", 2008, "Vermelho");

		veiculoRepository.save(veiculo1);

		// When /Act
		Veiculo saveVeiculo = veiculoRepository.findById(veiculo1.getId()).get();

		// Then / Assert
		assertNotNull(saveVeiculo);
		assertEquals(veiculo1.getId(), saveVeiculo.getId());

	}

	@DisplayName("Testando Update")
	@Test
	void testUpdateVeiculo() {

		// Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "chevrolet", "Corsa", 2006, "Preto");

		veiculoRepository.save(veiculo1);

		// When /Act
		Veiculo saveVeiculo = veiculoRepository.findById(veiculo1.getId()).get();
		veiculo1.setMarca("renault");
		veiculo1.setModelo("kwid");
		veiculo1.setAno(2016);
		veiculo1.setCor("branco");

		Veiculo updateVeiculo = veiculoRepository.save(saveVeiculo);

		// Then / Assert
		assertNotNull(updateVeiculo);
		assertEquals("renault", updateVeiculo.getMarca());
		assertEquals("kwid", updateVeiculo.getModelo());
		assertEquals(2016, updateVeiculo.getAno());
		assertEquals("branco", updateVeiculo.getCor());
	}

	@DisplayName("Testando o Delete")
	@Test
	void testDeleteVeiculo() {

		// Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "chevrolet", "Cruze", 2013, "Branco");

		veiculoRepository.save(veiculo1);

		// When /Act
		veiculoRepository.deleteById(veiculo1.getId());

		Optional<Veiculo> veiculoOptional = veiculoRepository.findById(veiculo1.getId());

		// Then / Assert	
		assertTrue(veiculoOptional.isEmpty());
	}
}
