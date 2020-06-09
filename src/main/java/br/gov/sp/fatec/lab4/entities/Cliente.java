package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cli_cliente")
@Data
@AttributeOverride(name = "id", column = @Column(name = "cli_id"))
public class Cliente extends Identificador {

    private String nome;
    private String cpf;
    private String endereco;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;
}
