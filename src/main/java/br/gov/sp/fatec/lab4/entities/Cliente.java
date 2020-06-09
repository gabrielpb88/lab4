package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Entity
@Table(name = "cli_cliente")
@Data
@AttributeOverride(name = "id", column = @Column(name = "cli_id"))
public abstract class Cliente extends Identificador {

    protected String nome;
    protected String endereco;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    protected List<Pedido> pedidos;
}
