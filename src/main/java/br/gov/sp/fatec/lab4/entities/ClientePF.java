package br.gov.sp.fatec.lab4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@DiscriminatorValue(value = "pf")
public class ClientePF extends Cliente {

    private String cpf;
}
