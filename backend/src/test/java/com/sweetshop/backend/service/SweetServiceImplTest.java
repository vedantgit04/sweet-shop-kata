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
}