package com.sweetshop.backend.service;

import com.sweetshop.backend.model.Sweet;
import java.util.List;

public interface SweetService {
    Sweet addSweet(Sweet sweet);
    List<Sweet> getAllSweets();
    List<Sweet> searchSweets(String keyword, Double minPrice, Double maxPrice);
    Sweet updateSweet(Long id, Sweet sweetDetails);
    void deleteSweet(Long id);
    Sweet purchaseSweet(Long id, Integer amount);
    Sweet restockSweet(Long id, Integer amount);// New method
}