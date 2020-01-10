package com.conductorcrud.simpleconductorcrud.repository;

import com.conductorcrud.simpleconductorcrud.model.Contas;
import com.conductorcrud.simpleconductorcrud.model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {

    List findByIdConta(Contas conta);
    List findByIdContaAndDataTransacaoBetween(Contas conta, Date dataInicial, Date dataFinal);
}
