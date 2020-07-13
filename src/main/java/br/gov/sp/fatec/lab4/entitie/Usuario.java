package br.gov.sp.fatec.lab4.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "usr_usuario")
public class Usuario {

    @Id
    private String usuario;

    private String senha;

    @ManyToMany
    @JoinTable(name = "usr_has_roles",
            joinColumns =
                {@JoinColumn(name = "usuario", referencedColumnName = "usuario")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    private Set<Role> roles = new HashSet<>();

    public boolean addRole(Role role){
        return roles.add(role);
    }

    public boolean hasHole(String string){
        Object array[] = roles.toArray();
        for(Object role : array){
            Role temp = (Role) role;
            if (temp.getRole().equals(string)){
                return true;
            }
        }
        return false;
    }

}
