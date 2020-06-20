package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="pag_pagamento_cartao")
public class PagamentoCartao extends Pagamento {

    private Integer parcelas;

    public Double getValorParcelas(){
        return this.valor / parcelas;
    }
}
