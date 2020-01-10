package com.conductorcrud.simpleconductorcrud.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Transacoes {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idTransacao;

    @ManyToOne
    @JoinColumn(name="idConta")
    private Contas idConta;

    private double valor;
    private Date dataTransacao;
    private String tipoTransacao;

    public Transacoes(Contas idConta, double valor, Date dataTransacao, String tipoTransacao) {
        this.idConta = idConta;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
        this.tipoTransacao = tipoTransacao;
    }

}
