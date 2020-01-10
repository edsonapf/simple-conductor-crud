package com.conductorcrud.simpleconductorcrud.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor //cria automaticamente um construtor com todas os atributos da classe como argumento
@NoArgsConstructor //cria automaticamente um construtor vazio (sem argumentos).
@Data //cria automaticamente os métodos toString, equals, hashCode, getters e setters.
@Entity
public class Contas {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idConta;

    @ManyToOne
//    @JoinColumns({
    @JoinColumn(name="idPessoa", referencedColumnName = "idPessoa")
//            @JoinColumn(name="cpf", referencedColumnName = "cpf")
//    })
    private Pessoas idPessoa;

    private double saldo;
    private double limiteSaqueDiario;
    private boolean flagAtivo;
    private int tipoConta;
    private Date dataCriacao;


    public boolean getFlagAtivo() {
        return this.flagAtivo;
    }

    public void setFlagAtivo(boolean flagAtivo) {
        this.flagAtivo = flagAtivo;
    }
}
