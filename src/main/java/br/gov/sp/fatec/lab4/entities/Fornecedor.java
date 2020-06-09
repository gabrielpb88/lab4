package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "for_fornecedor")
@Data
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "for_id")
    private Long id;
    private String nome;
    private String cnpj;

    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.EAGER)
    private Set<Item> items;
}
