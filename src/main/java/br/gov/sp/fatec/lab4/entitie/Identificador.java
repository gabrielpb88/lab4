package br.gov.sp.fatec.lab4.entitie;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public abstract class Identificador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Override
    public String toString() {
        return "Identificador{" +
                "id=" + id +
                '}';
    }
}
