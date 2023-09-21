package br.com.example.juspharus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.example.juspharus.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

}