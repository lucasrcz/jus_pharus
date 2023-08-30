package br.com.example.juspharus.repository;

import org.springframework.stereotype.Repository;

import br.com.example.juspharus.entity.Cliente;

@Repository
public class clienteRepository extends JpaRepository<Cliente> {
    
}
