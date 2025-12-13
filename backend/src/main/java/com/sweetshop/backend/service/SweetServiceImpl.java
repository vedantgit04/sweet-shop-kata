package com.sweetshop.backend.service;

import com.sweetshop.backend.model.Sweet;
import com.sweetshop.backend.repository.SweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SweetServiceImpl implements SweetService {

    private final SweetRepository sweetRepository;

    public SweetServiceImpl(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    @Override
    public Sweet addSweet(Sweet sweet) {
        return sweetRepository.save(sweet);
    }
    @Override
    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    @Override
    public List<Sweet> searchSweets(String keyword, Double minPrice, Double maxPrice) {
        return sweetRepository.searchSweets(keyword, minPrice, maxPrice);
    }

    @Override
    public Sweet updateSweet(Long id, Sweet sweetDetails) {
        return sweetRepository.findById(id).map(sweet -> {
            sweet.setName(sweetDetails.getName());
            sweet.setCategory(sweetDetails.getCategory());
            sweet.setPrice(sweetDetails.getPrice());
            sweet.setQuantity(sweetDetails.getQuantity());
            return sweetRepository.save(sweet);
        }).orElseThrow(() -> new RuntimeException("Sweet not found with id " + id));
    }

    @Override
    public void deleteSweet(Long id) {
        if (!sweetRepository.existsById(id)) {
            throw new RuntimeException("Sweet not found with id " + id);
        }
        sweetRepository.deleteById(id);
    }

    @Override
    public Sweet purchaseSweet(Long id, Integer amount) {
        if (amount <= 0) throw new RuntimeException("Purchase amount must be positive");

        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found with id " + id));

        if (sweet.getQuantity() < amount) {
            throw new RuntimeException("Insufficient stock. Requested: " + amount + ", Available: " + sweet.getQuantity());
        }

        sweet.setQuantity(sweet.getQuantity() - amount);
        return sweetRepository.save(sweet);
    }

    @Override
    public Sweet restockSweet(Long id, Integer amount) {
        if (amount <= 0) throw new RuntimeException("Restock amount must be positive");

        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sweet not found with id " + id));

        sweet.setQuantity(sweet.getQuantity() + amount);
        return sweetRepository.save(sweet);
    }
}
