package org.mql.spring.biblio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    private Author author1;
    private Author author2;
    private Author sampleAuthor;

    @BeforeEach
    void setUp() {
        author1 = new Author();
        author2 = new Author();
        sampleAuthor = new Author();
    }

    @Test
    @DisplayName("getAllAuthors() retourne tous les auteurs")
    void shouldReturnAllAuthors() {
        // Arrange
        when(authorRepository.findAll()).thenReturn(List.of(author1, author2));

        // Act
        List<Author> result = service.getAllAuthors();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertSame(author1, result.get(0));
        verify(authorRepository).findAll();
        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    @DisplayName("getAuthorById() retourne l'auteur lorsqu'il existe")
    void shouldReturnAuthorWhenFoundById() {
        // Arrange
        int id = 1;
        when(authorRepository.findById(id)).thenReturn(Optional.of(author1));

        // Act
        Author result = service.getAuthorById(id);

        // Assert
        assertNotNull(result);
        assertSame(author1, result);
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

    @Test
    @DisplayName("getAuthorByName() retourne les auteurs correspondant au mot-clé")
    void shouldReturnAuthorsMatchingKeyword() {
        // Arrange
        String keyword = "Hugo";
        when(authorRepository.findByKeyword(keyword)).thenReturn(List.of(author1));

        // Act
        List<Author> result = service.getAuthorByName(keyword);

        // Assert
        assertEquals(1, result.size());
        assertSame(author1, result.get(0));
        verify(authorRepository).findByKeyword(keyword);
    }

    @Test
    @DisplayName("addAuthor() retourne false tant que la méthode n'est pas implémentée")
    void shouldReturnFalseForAddAuthorWhenNotImplemented() {
        // Act
        boolean added = service.addAuthor(sampleAuthor);

        // Assert
        assertFalse(added);
    }

    @Test
    @DisplayName("saveAuthor() retourne false tant que la méthode n'est pas implémentée")
    void shouldReturnFalseForSaveAuthorWhenNotImplemented() {
        // Act
        boolean saved = service.saveAuthor(sampleAuthor);

        // Assert
        assertFalse(saved);
    }

    @Test
    @DisplayName("removeAuthor() retourne null tant que la méthode n'est pas implémentée")
    void shouldReturnNullForRemoveAuthorWhenNotImplemented() {
        // Arrange
        int id = 1;

        // Act
        Author removed = service.removeAuthor(id);

        // Assert
        assertNull(removed);
    }
}