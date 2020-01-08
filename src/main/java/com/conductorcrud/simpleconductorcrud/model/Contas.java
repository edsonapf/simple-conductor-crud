package com.conductorcrud.simpleconductorcrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Date;

@AllArgsConstructor //cria automaticamente um construtor com todas os atributos da classe como argumento
@NoArgsConstructor //cria automaticamente um construtor vazio (sem argumentos).
@Data //cria automaticamente os métodos toString, equals, hashCode, getters e setters.
@Entity
public class Contas {

    @idConta
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idConta;

    private Long idPessoa;
    private double saldo;
    private double limiteSaqueDiario;
    private boolean flagAtivo;
    private int tipoConta;
    private Date dataCriacao;

}
