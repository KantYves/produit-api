package com.produit.produitservice.service;

import com.produit.produitservice.model.Produit;
import com.produit.produitservice.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProduitService {
    private final ProduitRepository produitRepository;

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit getProduitById(Long id) {
        Optional<Produit> optionalProduit = produitRepository.findById(id);

        if (optionalProduit.isEmpty()) {
            throw new RuntimeException("Produit inexistant");
        }
        return optionalProduit.get();
    }


    public String deleteProduitById(Long idProduit) {
        Optional<Produit> optionalProduit = produitRepository.findById(idProduit);

        if (optionalProduit.isEmpty()) {
            throw new RuntimeException("Suppression impossible car le produit n'existe pas ");
        }
        produitRepository.delete(optionalProduit.get());

        return "Produit supprimé avec succes";
    }

    public Produit editProduit(Long id, Produit produit) {
        Optional<Produit> optionalProduit = produitRepository.findById(id);

        if(optionalProduit.isEmpty()) {
            throw new RuntimeException("Modification impossible ");
        }

        Produit produitAModifier = optionalProduit.get();

        produitAModifier.setName(produit.getName());
        produitAModifier.setPrice(produit.getPrice());

        return produitRepository.save(produitAModifier);
    }
}
