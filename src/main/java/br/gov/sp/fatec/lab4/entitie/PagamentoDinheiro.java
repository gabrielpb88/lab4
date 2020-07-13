package br.gov.sp.fatec.lab4.entitie;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "pag_pagamento_dinheiro")
public class PagamentoDinheiro extends Pagamento {

    private Double desconto;

    public Double getValor() {
        return this.valor - (this.valor * (desconto / 100));
    }

    @Override
    public String toString() {
        return "PagamentoDinheiro{" +
                "desconto=" + desconto +
                ", valor=" + valor +
                ", pedido=" + pedido +
                ", id=" + id +
                '}';
    }
}
