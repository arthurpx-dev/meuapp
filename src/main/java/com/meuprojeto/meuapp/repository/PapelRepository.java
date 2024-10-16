package com.meuprojeto.meuapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuprojeto.meuapp.model.Papel;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {

    Optional<Papel> findByNome(String nome);

}
