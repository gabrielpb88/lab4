package br.gov.sp.fatec.lab4.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "for_fornecedor")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "for_id"))
public class Fornecedor extends Identificador {

    private String nome;
    private String cnpj;

    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    @Override
    public String toString() {
        return "Fornecedor{" +
                "nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", items=" + items +
                ", id=" + id +
                '}';
    }
}
