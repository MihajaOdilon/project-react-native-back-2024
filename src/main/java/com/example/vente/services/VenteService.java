package com.example.vente.services;

import com.example.vente.entites.VenteEntity;
import com.example.vente.repositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VenteService {
    @Autowired
    private VenteRepository venteRepository;
    public Collection<VenteEntity> getAll(){
        return venteRepository.findAll();
    }
    public VenteEntity getById(Long id){
        return venteRepository.findById(id).orElse(null);
    }
    public VenteEntity update(Long id, VenteEntity nouvelleVente){
        VenteEntity vente = this.getById(id);
        if (vente!=null){
            vente.setDesign(nouvelleVente.getDesign());
            vente.setNumProduit(nouvelleVente.getNumProduit());
            vente.setPrix(nouvelleVente.getPrix());
            vente.setQuantite(nouvelleVente.getQuantite());
            return venteRepository.save(vente);
        }
        else return null;
    }
    public void create(VenteEntity vente){
        venteRepository.save(vente);
    }
    public void delete(VenteEntity vente){
        venteRepository.delete(vente);
    }
}
