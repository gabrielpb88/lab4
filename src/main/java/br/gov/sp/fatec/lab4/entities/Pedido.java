package br.gov.sp.fatec.lab4.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ped_pedido")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "cli_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "item_pedido",
            joinColumns =
                    { @JoinColumn(name = "ped_id")},
    inverseJoinColumns = { @JoinColumn(name = "ite_id")})
    private List<Item> items;

}
