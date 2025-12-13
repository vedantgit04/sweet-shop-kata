package com.sweetshop.backend.repository;

import com.sweetshop.backend.model.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SweetRepository extends JpaRepository<Sweet, Long> {

    // This ONE query handles Name, Category, and Price Range all at once.
    @Query("SELECT s FROM Sweet s WHERE " +
            "(:keyword IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(s.category) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:minPrice IS NULL OR s.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR s.price <= :maxPrice)")
    List<Sweet> searchSweets(
            @Param("keyword") String keyword,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
}