package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class Identificador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
}
