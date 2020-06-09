package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cli_cliente")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private Long id;
    private String nome;
    private String cpf;
    private String endereco;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;
}
