package com.meuprojeto.meuapp.repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meuprojeto.meuapp.model.Papel;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {

    Optional<Papel> findByNome(String nome);

     @Query(value = "SELECT p FROM Papel p JOIN p.usuarios u WHERE u.uuid = :usuarioUuid")
    Set<Papel> findByUsuarioUuid(UUID usuarioUuid);


    

}
