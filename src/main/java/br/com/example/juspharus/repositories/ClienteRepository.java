package br.com.example.juspharus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.example.juspharus.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}