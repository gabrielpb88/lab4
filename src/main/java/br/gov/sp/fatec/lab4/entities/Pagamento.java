package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "pag_pagamento")
@Data
@AttributeOverride(name = "id", column = @Column(name = "pag_id"))
public abstract class Pagamento extends Identificador {

    protected Double valor;

    @ManyToOne
    @JoinColumn(name = "ped_id")
    protected Pedido pedido;
}