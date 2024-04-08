package com.example.vente.repositories;

import com.example.vente.entites.VenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenteRepository extends JpaRepository<VenteEntity, Long> {
    Optional<VenteEntity> findByNumProduit(Long numProduit);
}
