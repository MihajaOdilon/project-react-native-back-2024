package com.example.vente.entites;

import jakarta.persistence.*;

@Entity
public class VenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , unique = true)
    private Long numProduit;
    @Column(nullable = false)
    private String design;
    @Column(nullable = false)
    private Double prix;
    @Column(nullable = false)
    private Integer quantite;

    public Long getNumProduit() {
        return numProduit;
    }

    public void setNumProduit(Long numProduit) {
        this.numProduit = numProduit;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
