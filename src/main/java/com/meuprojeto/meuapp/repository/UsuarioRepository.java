package com.meuprojeto.meuapp.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuprojeto.meuapp.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Usuario findByUuid(UUID uuid);

    Optional<Usuario> findByEmail(String email);
}
