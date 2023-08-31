package br.com.example.juspharus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.example.juspharus.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}