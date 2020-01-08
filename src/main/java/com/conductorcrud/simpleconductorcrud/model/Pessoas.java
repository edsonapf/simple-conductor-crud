package com.conductorcrud.simpleconductorcrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pessoas {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idPessoa;

    private String nome;
    private String cpf;
    private Date dataNascimento;

}
