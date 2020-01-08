package com.conductorcrud.simpleconductorcrud.repository;

import com.conductorcrud.simpleconductorcrud.model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {
}
