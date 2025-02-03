package com.br.VendasIQ.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.VendasIQ.model.registroUsuario;

public interface UserDao extends JpaRepository<registroUsuario, Integer> {
	Optional<registroUsuario> findByEmail(String email);
}