package com.example.vente.controllers;

import com.example.vente.entites.VenteEntity;
import com.example.vente.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ventes")
public class VenteController {
    private final VenteService venteService;
    @Autowired
    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }
    @PostMapping
    public ResponseEntity<VenteEntity> create(@RequestBody VenteEntity vente) {
        try {
            VenteEntity newVente = venteService.create(vente);
            return new ResponseEntity<>(newVente, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenteEntity> getById(@PathVariable Long id){
        VenteEntity vente = venteService.getById(id);
        if (vente!=null){
            return ResponseEntity.ok(vente);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<Collection<VenteEntity>> getAll(){
        return ResponseEntity.ok(venteService.getAll());
    }
    @PutMapping("/{id}")
    public ResponseEntity<VenteEntity> update(@PathVariable Long id, @RequestBody VenteEntity vente) {
        try {
            VenteEntity updatedVente = venteService.update(id, vente);
            return ResponseEntity.ok(updatedVente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        venteService.delete(id);
    }
    @GetMapping("/minimal-price")
    public ResponseEntity<Double> getMinimalPrice() {
        double minimalPrice = venteService.getMinimalPrice();
        return new ResponseEntity<>(minimalPrice, HttpStatus.OK);
    }
    @GetMapping("/maximal-price")
    public ResponseEntity<Double> getMaximalPrice() {
        double maximalPrice = venteService.getMaximalPrice();
        return new ResponseEntity<>(maximalPrice, HttpStatus.OK);
    }
    @GetMapping("/total-amount")
    public ResponseEntity<Double> getTotalAmount() {
        double totalAmount = venteService.getTotalAmount();
        return new ResponseEntity<>(totalAmount, HttpStatus.OK);
    }
}



