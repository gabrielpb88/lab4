package br.gov.sp.fatec.lab4.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class Identificador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;
}
