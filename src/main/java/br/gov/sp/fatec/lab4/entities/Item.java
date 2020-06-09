package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ite_item")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ite_id")
    private Long id;
    private String nome;
    private Double preco;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "for_id")
    private Fornecedor fornecedor;

}
