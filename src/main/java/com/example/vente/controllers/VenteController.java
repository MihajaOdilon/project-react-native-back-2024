package com.example.vente.controllers;

import com.example.vente.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventes")
public class VenteController {
    @Autowired
    private VenteService venteService;


}
