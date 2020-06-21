package br.gov.sp.fatec.lab4.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "pj")
public class ClientePJ extends Cliente {

    private String cnpj;

    @Override
    public String toString() {
        return "ClientePJ{" +
                "cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", pedidos=" + pedidos +
                ", id=" + id +
                '}';
    }
}
