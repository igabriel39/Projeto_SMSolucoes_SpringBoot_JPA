package com.example.SmSolucoes.repository;

import com.example.SmSolucoes.model.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<VendaModel, Integer> {
}
