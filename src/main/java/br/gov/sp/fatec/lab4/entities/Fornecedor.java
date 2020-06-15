package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "for_fornecedor")
@Data
@AttributeOverride(name = "id", column = @Column(name = "for_id"))
public class Fornecedor extends Identificador {

    private String nome;
    private String cnpj;

    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();
}
