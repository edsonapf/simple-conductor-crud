package com.conductorcrud.simpleconductorcrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.sql.Date;

@AllArgsConstructor //cria automaticamente um construtor com todas os atributos da classe como argumento
@NoArgsConstructor //cria automaticamente um construtor vazio (sem argumentos).
@Data //cria automaticamente os métodos toString, equals, hashCode, getters e setters.
@Entity
public class Transacoes {

    private int idTransacao;
    private int idConta;
    private double valor;
    private Date dataTransacao;

}