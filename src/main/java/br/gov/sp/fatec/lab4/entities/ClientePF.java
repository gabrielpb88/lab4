package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue(value = "pf")
public class ClientePF extends Cliente {

    private String cpf;
}
