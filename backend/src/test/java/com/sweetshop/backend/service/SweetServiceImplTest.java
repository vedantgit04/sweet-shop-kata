package com.sweetshop.backend.service;

import com.sweetshop.backend.model.Sweet;
import com.sweetshop.backend.repository.SweetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*; // Ensure these are imported

@ExtendWith(MockitoExtension.class)
class SweetServiceTest {

    @Mock
    private SweetRepository sweetRepository;

    @InjectMocks
    private SweetServiceImpl sweetService;

    @Test
    void shouldAddSweetSuccessfully() {
        // Arrange
        Sweet sweet = new Sweet("Jalebi", "Sugar Syrup", 5.99, 100);
        when(sweetRepository.save(any(Sweet.class))).thenReturn(sweet);

        // Act
        Sweet savedSweet = sweetService.addSweet(sweet);

        // Assert
        assertNotNull(savedSweet, "Saved sweet should not be null");
        assertEquals("Jalebi", savedSweet.getName());
        verify(sweetRepository, times(1)).save(sweet);
    }
    @Test
    void shouldReturnAllSweets() {
        // Arrange
        Sweet s1 = new Sweet("Laddu", "Traditional", 10.0, 50);
        Sweet s2 = new Sweet("Kaju Katli", "Premium", 20.0, 30);

        // Tell the mock: "When someone asks for findAll(), return this list"
        when(sweetRepository.findAll()).thenReturn(Arrays.asList(s1, s2));

        // Act
        List<Sweet> result = sweetService.getAllSweets();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Laddu", result.get(0).getName());

        // Verify the repository was actually called
        verify(sweetRepository, times(1)).findAll();
    }

    @Test
    void shouldSearchSweets() {
        Sweet sweet = new Sweet("Kaju Katli", "Premium", 20.0, 30);
        when(sweetRepository.searchSweets("Kaju", null, null))
                .thenReturn(Collections.singletonList(sweet));

        List<Sweet> result = sweetService.searchSweets("Kaju", null, null);

        assertEquals(1, result.size());
        verify(sweetRepository).searchSweets("Kaju", null, null);
    }

    @Test
    void shouldUpdateSweet() {
        Sweet existing = new Sweet("Old Name", "Old Cat", 10.0, 10);
        existing.setId(1L);
        Sweet updateInfo = new Sweet("New Name", "New Cat", 15.0, 20);

        when(sweetRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(sweetRepository.save(existing)).thenReturn(existing);

        Sweet updated = sweetService.updateSweet(1L, updateInfo);

        assertEquals("New Name", updated.getName());
        assertEquals("New Cat", updated.getCategory());
        assertEquals(15.0, updated.getPrice());
    }

    @Test
    void shouldDeleteSweet() {
        when(sweetRepository.existsById(1L)).thenReturn(true);

        sweetService.deleteSweet(1L);

        verify(sweetRepository).deleteById(1L);
    }

    @Test
    void shouldPurchaseSweet() {
        Sweet sweet = new Sweet("Ladoo", "Trad", 10.0, 100);
        sweet.setId(1L);

        when(sweetRepository.findById(1L)).thenReturn(Optional.of(sweet));
        when(sweetRepository.save(sweet)).thenReturn(sweet);

        Sweet result = sweetService.purchaseSweet(1L, 10);

        assertEquals(90, result.getQuantity()); // 100 - 10
    }

    @Test
    void shouldRestockSweet() {
        Sweet sweet = new Sweet("Ladoo", "Trad", 10.0, 50);
        sweet.setId(1L);

        when(sweetRepository.findById(1L)).thenReturn(Optional.of(sweet));
        when(sweetRepository.save(sweet)).thenReturn(sweet);

        Sweet result = sweetService.restockSweet(1L, 20);

        assertEquals(70, result.getQuantity()); // 50 + 20
    }
}