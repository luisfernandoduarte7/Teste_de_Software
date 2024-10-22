package com.teste.concessionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.concessionaria.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{

}
