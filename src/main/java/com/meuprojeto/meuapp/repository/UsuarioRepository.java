package com.meuprojeto.meuapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meuprojeto.meuapp.model.Papel;
import com.meuprojeto.meuapp.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Usuario findByUuid(UUID uuid);

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u.papeis FROM Usuario u WHERE u.email = :email")
    List<Papel> findPapeisByEmail(@Param("email") String email);
}
