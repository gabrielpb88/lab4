package br.gov.sp.fatec.lab4.entitie;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "pag_pagamento")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "pag_id"))
public abstract class Pagamento extends Identificador {

    protected Double valor;

    @ManyToOne
    @JoinColumn(name = "ped_id")
    protected Pedido pedido;
}
