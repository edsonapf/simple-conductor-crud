package com.conductorcrud.simpleconductorcrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//@IdClass(PessoasId.class)
public class Pessoas {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idPessoa;

    private String cpf;
    private String nome;
    private Date dataNascimento;

}
