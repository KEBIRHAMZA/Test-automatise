package org.mql.spring.biblio.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mql.spring.biblio.models.Author;
import org.mql.spring.biblio.services.BiblioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@DisplayName("Tests d'intégration du service de bibliothèque")
class BiblioServiceIntegrationTest {

    @Autowired
    private BiblioService biblioService;

    @Test
    @DisplayName("Enregistrer un auteur avec succès")
    void shouldSaveAuthorSuccessfully() {
        // Arrange
        Author author = new Author();
        author.setName("Bjarne Stroustrup");
        author.setYearBorn(1950);
        
        // Act
        boolean result = biblioService.saveAuthor(author);

        // Assert
        assertTrue(result);
        assertNotNull(author.getId());
    }

}
