package org.mql.spring.biblio.services;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mql.spring.biblio.models.Author;
import org.mql.spring.biblio.repositories.AuthorRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitaires de BiblioServiceDefault")
class BiblioServiceDefaultTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BiblioServiceDefault service;

    private Author author;


    @BeforeEach
    void setUp() {
        author = new Author();
    }

    @Test
    @DisplayName("getAuthorById() retourne l'auteur lorsqu'il existe")
    void shouldReturnAuthorWhenFoundById() {
        // Arrange
        int id = 1;
        when(authorRepository.findById(id)).thenReturn(Optional.of(author));

        // Act
        Author result = service.getAuthorById(id);

        // Assert
        assertNotNull(result);
        assertSame(author, result);
        verify(authorRepository).findById(id);
    }

    @Test
    @DisplayName("getAuthorById() retourne null lorsque l'identifiant est introuvable")
    void shouldReturnNullWhenAuthorNotFoundById() {
        // Arrange
        int id = 99;
        when(authorRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Author result = service.getAuthorById(id);

        // Assert
        assertNull(result);
        verify(authorRepository).findById(id);
    }

    // ID-3 : cas limite (nouveau test)
    @Test
    @DisplayName("retourne null lorsque l'identifiant est négatif")
    void shouldReturnNullWhenIdIsNegative() {
        // Arrange
        int id = -1;
        when(authorRepository.findById(id)).thenReturn(Optional.empty());
 
        // Act
        Author result = service.getAuthorById(id);
 
        // Assert
        assertNull(result);
        verify(authorRepository).findById(id);
    }
    
}