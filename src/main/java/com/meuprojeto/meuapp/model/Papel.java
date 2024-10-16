package com.meuprojeto.meuapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "PAPEL")
public class Papel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", unique = true, nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "papeis")
    private Set<Usuario> usuarios;

}
