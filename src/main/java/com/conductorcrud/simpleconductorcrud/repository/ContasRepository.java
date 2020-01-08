package com.conductorcrud.simpleconductorcrud.repository;

import com.conductorcrud.simpleconductorcrud.model.Contas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasRepository extends JpaRepository<Contas, Long> {
}
