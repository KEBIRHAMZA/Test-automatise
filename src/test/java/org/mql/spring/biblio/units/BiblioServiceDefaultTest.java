package org.mql.spring.biblio.units;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mql.spring.biblio.models.Author;
import org.mql.spring.biblio.repositories.AuthorRepository;
import org.mql.spring.biblio.services.BiblioServiceDefault;

@ExtendWith(MockitoExtension.class)
@DisplayName("Démonstration JaCoCo (Vert / Jaune / Rouge) sur BiblioServiceDefault")
class BiblioServiceDefaultTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BiblioServiceDefault service;

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1);
    }

    // --- VERT : méthodes et branches entièrement couvertes ---

    @Test
    @DisplayName("[VERT] getAllAuthors() retourne la liste complète")
    void shouldReturnAllAuthors() {
        List<Author> expected = List.of(author, new Author());
        when(authorRepository.findAll()).thenReturn(expected);

        List<Author> result = service.getAllAuthors();

        assertEquals(2, result.size());
        assertSame(expected, result);
        verify(authorRepository).findAll();
    }

    @Test
    @DisplayName("[VERT] getAuthorById() retourne l'auteur existant")
    void shouldReturnAuthorWhenFoundById() {
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        Author result = service.getAuthorById(1);

        assertNotNull(result);
        assertSame(author, result);
        verify(authorRepository).findById(1);
    }

    @Test
    @DisplayName("[VERT] getAuthorById() retourne null si introuvable")
    void shouldReturnNullWhenAuthorNotFoundById() {
        when(authorRepository.findById(99)).thenReturn(Optional.empty());

        Author result = service.getAuthorById(99);

        assertNull(result);
        verify(authorRepository).findById(99);
    }

    @Test
    @DisplayName("[VERT] getAuthorByName() retourne les auteurs correspondants")
    void shouldReturnAuthorsMatchingName() {
        List<Author> expected = List.of(author);
        when(authorRepository.findByNameContainingIgnoreCase("hugo")).thenReturn(expected);

        List<Author> result = service.getAuthorByName("hugo");

        assertEquals(1, result.size());
        assertSame(expected, result);
        verify(authorRepository).findByNameContainingIgnoreCase("hugo");
    }

    @Test
    @DisplayName("[VERT] saveAuthor() retourne false si l'auteur est null")
    void shouldReturnFalseWhenSavingNullAuthor() {
        boolean result = service.saveAuthor(null);

        assertFalse(result);
        verify(authorRepository, never()).save(any());
    }

    @Test
    @DisplayName("[VERT] removeAuthor() supprime et retourne l'auteur existant")
    void shouldRemoveAndReturnAuthorWhenFound() {
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        Author result = service.removeAuthor(1);

        assertNotNull(result);
        assertSame(author, result);
        verify(authorRepository).findById(1);
        verify(authorRepository).deleteById(1);
    }

    // --- JAUNE : branche partiellement couverte dans addAuthor() ---
    // On teste author == null et existsById == false, mais pas existsById == true.

    @Test
    @DisplayName("[JAUNE] addAuthor() avec author null (branche gauche du ||)")
    void shouldReturnFalseWhenAddingNullAuthor() {
        boolean result = service.addAuthor(null);

        assertFalse(result);
        verify(authorRepository, never()).existsById(any());
        verify(authorRepository, never()).save(any());
    }

    @Test
    @DisplayName("[JAUNE] addAuthor() avec auteur valide et absent (existsById == false)")
    void shouldAddAuthorWhenValidAndNotExisting() {
        when(authorRepository.existsById(1)).thenReturn(false);
        when(authorRepository.save(author)).thenReturn(author);

        boolean result = service.addAuthor(author);

        assertTrue(result);
        verify(authorRepository).existsById(1);
        verify(authorRepository).save(author);
    }

    
}