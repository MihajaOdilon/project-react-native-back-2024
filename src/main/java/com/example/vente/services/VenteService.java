package com.example.vente.services;

import com.example.vente.entites.VenteEntity;
import com.example.vente.repositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class VenteService {
    private final VenteRepository venteRepository;
    @Autowired
    public VenteService(VenteRepository venteRepository) {
        this.venteRepository = venteRepository;
    }

    public VenteEntity create(VenteEntity vente) throws Exception {
        if (vente.getNumProduit() == null || vente.getDesign() == null || vente.getPrix() == null || vente.getQuantite() == null) {
            throw new IllegalArgumentException("Tous les champs sont obligatoires");
        }
        Optional<VenteEntity> existingVente = venteRepository.findByNumProduit(vente.getNumProduit());
        if (existingVente.isPresent()) {
            throw new Exception("Le numéro de produit est déjà utilisé");
        }
        return venteRepository.save(vente);
    }
    public Collection<VenteEntity> getAll(){
        return venteRepository.findAll();
    }
    public VenteEntity getById(Long id){
        return venteRepository.findById(id).orElse(null);
    }
    public VenteEntity update(Long id, VenteEntity updatedVente) throws Exception {
        if (updatedVente.getNumProduit() == null || updatedVente.getDesign() == null || updatedVente.getPrix() == null || updatedVente.getQuantite() == null) {
            throw new IllegalArgumentException("Tous les champs sont obligatoires");
        }
        VenteEntity venteToUpdate = venteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vente non trouvée pour l'ID: " + id));
        if (!venteToUpdate.getNumProduit().equals(updatedVente.getNumProduit())) {
            Optional<VenteEntity> existingVente = venteRepository.findByNumProduit(updatedVente.getNumProduit());
            if (existingVente.isPresent()) {
                throw new Exception("Le numéro de produit est déjà utilisé");
            }
        }
        venteToUpdate.setNumProduit(updatedVente.getNumProduit());
        venteToUpdate.setDesign(updatedVente.getDesign());
        venteToUpdate.setPrix(updatedVente.getPrix());
        venteToUpdate.setQuantite(updatedVente.getQuantite());
        return venteRepository.save(venteToUpdate);
    }

    public void delete(Long id){
        venteRepository.deleteById(id);
    }
    public double getMinimalPrice() {
        List<VenteEntity> venteEntities = venteRepository.findAll();
        if (venteEntities.isEmpty()) {
            return 0.0;
        }
        double minPrice = venteEntities.get(0).getPrix();
        for (VenteEntity vente : venteEntities) {
            if (vente.getPrix() < minPrice) {
                minPrice = vente.getPrix();
            }
        }
        return minPrice;
    }
    public double getMaximalPrice() {
        List<VenteEntity> venteEntities = venteRepository.findAll();
        if (venteEntities.isEmpty()) {
            return 0.0;
        }
        double maxPrice = venteEntities.get(0).getPrix();
        for (VenteEntity vente : venteEntities) {
            if (vente.getPrix() > maxPrice) {
                maxPrice = vente.getPrix();
            }
        }
        return maxPrice;
    }
    public double getTotalAmount() {
        List<VenteEntity> venteEntities = venteRepository.findAll();
        double totalAmount = 0.0;
        for (VenteEntity vente : venteEntities) {
            totalAmount += vente.getPrix() * vente.getQuantite();
        }
        return totalAmount;
    }
}
