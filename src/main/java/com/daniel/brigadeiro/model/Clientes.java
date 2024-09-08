package com.daniel.brigadeiro.model;

import java.util.ArrayList;
import java.util.List;

import com.daniel.brigadeiro.model.DTO.ClientesDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Clientes {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Email
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente_fk")
    private List<Pedidos> pedidos = new ArrayList<>();


    public Clientes(ClientesDTO objDTO) {
        this.id = objDTO.getId();
        this.nome = objDTO.getNome();
        this.email = objDTO.getEmail();
    }
}
