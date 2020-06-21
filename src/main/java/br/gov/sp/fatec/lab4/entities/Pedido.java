package br.gov.sp.fatec.lab4.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ped_pedido")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "ped_id"))
public class Pedido extends Identificador{

    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "item_pedido",
            joinColumns =
                    {@JoinColumn(name = "ped_id")},
            inverseJoinColumns = {@JoinColumn(name = "ite_id")})
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<Pagamento> pagamentos = new ArrayList<>();

    @Override
    public String toString() {
        return "Pedido{" +
                "cliente=" + cliente +
                ", items=" + items +
                ", pagamentos=" + pagamentos +
                ", id=" + id +
                '}';
    }
}
