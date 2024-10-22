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

import com.teste.concessionaria.entity.Cliente;

@DataJpaTest
class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;

	@DisplayName("Testando o Save")
	@Test
	void testSalvarRepository() {

		// Given / Arrange
		Cliente cliente1 = new Cliente(null, "Adauto", "(00)0000-0000", "12995795829", "24.429.478-1");
		// When / Act
		Cliente saveCliente = clienteRepository.save(cliente1);

		// Then /Assert
		assertNotNull(saveCliente);
		assertTrue(saveCliente.getId() > 0);

	}

	@DisplayName("Testando GET para todos os clientes")
	@Test
	void testGetAllRepository() {

		// Given / Arrange
		Cliente cliente1 = new Cliente(null, "Adauto", "(00)0000-0000", "12995795829", "24.429.478-1");

		Cliente cliente2 = new Cliente(null, "Andre", "(00)0000-0000", "23067163826", "32.126.746-1");

		clienteRepository.save(cliente1);
		clienteRepository.save(cliente2);

		// When /Act
		List<Cliente> clienteList = clienteRepository.findAll();

		// Then / Assert
		assertNotNull(clienteList);
		assertEquals(2, clienteList.size());

	}

	@DisplayName("Testando GET ById")
	@Test
	void testGetById() {

		// Given / Arrange
		Cliente cliente1 = new Cliente(null, "Adauto", "(00)0000-0000", "12995795829", "24.429.478-1");

		clienteRepository.save(cliente1);

		// When /Act
		Cliente saveCliente = clienteRepository.findById(cliente1.getId()).get();

		// Then / Assert
		assertNotNull(saveCliente);
		assertEquals(cliente1.getId(), saveCliente.getId());

	}

	@DisplayName("Testando Update")
	@Test
	void testUpdateCliente() {

		// Given / Arrange
		Cliente cliente1 = new Cliente(null, "Samuel", "(00)0000-0000", "59653351800", "41.877.020-7");

		clienteRepository.save(cliente1);

		// When /Act
		Cliente saveCliente = clienteRepository.findById(cliente1.getId()).get();
		cliente1.setNome("Zico");
		cliente1.setTelefone("(00)0000-0000");
		cliente1.setCpf("49454787837");
		cliente1.setRg("49.746.787-2");

		Cliente updateCliente = clienteRepository.save(saveCliente);

		// Then / Assert
		assertNotNull(updateCliente);
		assertEquals("Zico", updateCliente.getNome());
		assertEquals("(00)0000-0000", updateCliente.getTelefone());
		assertEquals("49454787837", updateCliente.getCpf());
		assertEquals("49.746.787-2", updateCliente.getRg());
	}

	@DisplayName("Testando o Delete")
	@Test
	void testDeleteCliente() {

		// Given / Arrange
		Cliente cliente1 = new Cliente(null, "Adriano", "(00)0000-0000", "76365275889", "29.131.340-1");

		clienteRepository.save(cliente1);

		// When /Act
		clienteRepository.deleteById(cliente1.getId());

		Optional<Cliente> clienteOptional = clienteRepository.findById(cliente1.getId());

		// Then / Assert
		assertTrue(clienteOptional.isEmpty());
	}
}
