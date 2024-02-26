package com.example.vente.repositories;

import com.example.vente.entites.VenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class VenteRepository implements JpaRepository<VenteEntity, Long> {
}
