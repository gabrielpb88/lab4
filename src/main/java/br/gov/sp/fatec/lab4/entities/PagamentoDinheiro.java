package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="pag_pagamento_dinheiro")
public class PagamentoDinheiro extends Pagamento {

    private Double desconto;

    public Double getValor(){
        return this.valor - (this.valor * (desconto/100));
    }
}
