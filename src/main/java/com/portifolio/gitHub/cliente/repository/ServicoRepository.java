package com.portifolio.gitHub.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.gitHub.cliente.models.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}
