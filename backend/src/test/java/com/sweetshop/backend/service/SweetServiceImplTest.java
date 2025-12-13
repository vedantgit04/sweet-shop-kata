package com.sweetshop.backend.service;

import com.sweetshop.backend.model.Sweet;
import com.sweetshop.backend.repository.SweetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}