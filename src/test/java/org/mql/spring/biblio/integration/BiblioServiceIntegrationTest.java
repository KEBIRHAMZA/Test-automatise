package org.mql.spring.biblio.integration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.mql.spring.biblio.services.BiblioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BiblioServiceIntegrationTest {

    @Autowired
    private BiblioService biblioService;

    @Test
    void shouldReturnAllAuthors() {
        assertNotNull(biblioService.getAllAuthors());
    }

    @Test
    void shouldReturnNullWhenAuthorDoesNotExist() {
        assertNull(biblioService.getAuthorById(-1));
    }

    @Test
    void shouldRejectNullAuthor() {
        assertFalse(biblioService.addAuthor(null));
    }
}