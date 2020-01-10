package com.conductorcrud.simpleconductorcrud.repository;

import com.conductorcrud.simpleconductorcrud.model.Contas;
import com.conductorcrud.simpleconductorcrud.model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {
    List findByIdContaOrderByDataTransacao(Contas conta);
}
