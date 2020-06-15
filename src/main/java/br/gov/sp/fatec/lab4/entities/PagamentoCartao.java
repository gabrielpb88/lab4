package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="pag_pagamento_cartao")
@AttributeOverride(name = "id", column = @Column(name = "pag_id"))
public class PagamentoCartao extends Pagamento {

    private Integer parcelas;

    public Double getValorParcelas(){
        return this.valor / parcelas;
    }
}
