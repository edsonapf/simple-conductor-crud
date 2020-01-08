package com.conductorcrud.simpleconductorcrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Transacoes {

    @idTransacao
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idTransacao;

    private Long idConta;
    private double valor;
    private Date dataTransacao;

}
