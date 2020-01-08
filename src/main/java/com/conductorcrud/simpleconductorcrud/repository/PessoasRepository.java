package com.conductorcrud.simpleconductorcrud.repository;

import com.conductorcrud.simpleconductorcrud.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoasRepository extends JpaRepository<Pessoas, Long> {
}
