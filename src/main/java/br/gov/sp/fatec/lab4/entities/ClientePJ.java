package br.gov.sp.fatec.lab4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue(value = "pj")
public class ClientePJ extends Cliente {

    private String cnpj;
}
