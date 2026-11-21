package org.mql.spring.biblio.repositories;

import java.util.List;
import java.util.Optional;

import org.mql.spring.biblio.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    // ==========================
    // Recherche simple
    // ==========================
    List<Author> findByName(String name);

    List<Author> findByYearBorn(int yearBorn);

    Optional<Author> findById(Integer id);

    // ==========================
    // LIKE
    // ==========================
    List<Author> findByNameContaining(String keyword);

    List<Author> findByNameStartingWith(String prefix);

    List<Author> findByNameEndingWith(String suffix);

    List<Author> findByNameLike(String pattern);

    // ==========================
    // Comparaisons numériques
    // ==========================
    List<Author> findByYearBornGreaterThan(int year);

    List<Author> findByYearBornGreaterThanEqual(int year);

    List<Author> findByYearBornLessThan(int year);

    List<Author> findByYearBornLessThanEqual(int year);

    List<Author> findByYearBornBetween(int start, int end);

    // ==========================
    // Conditions multiples
    // ==========================
    List<Author> findByNameAndYearBorn(String name, int yearBorn);

    List<Author> findByNameOrYearBorn(String name, int yearBorn);

    // ==========================
    // IN / NOT IN
    // ==========================
    List<Author> findByYearBornIn(List<Integer> years);

    List<Author> findByYearBornNotIn(List<Integer> years);

    // ==========================
    // Null / Not Null
    // ==========================
    List<Author> findByNameIsNull();

    List<Author> findByNameIsNotNull();

    // ==========================
    // Ignore Case
    // ==========================
    List<Author> findByNameIgnoreCase(String name);

    List<Author> findByNameContainingIgnoreCase(String keyword);

    // ==========================
    // Tri
    // ==========================
    List<Author> findByNameOrderByYearBornAsc(String name);

    List<Author> findByNameOrderByYearBornDesc(String name);

    // ==========================
    // Top / First
    // ==========================
    Author findTopByOrderByYearBornAsc();

    Author findTopByOrderByYearBornDesc();

    List<Author> findFirst3ByOrderByYearBornDesc();

    // ==========================
    // Exists
    // ==========================
    boolean existsByName(String name);

    boolean existsByNameAndYearBorn(String name, int yearBorn);

    // ==========================
    // Count
    // ==========================
    long countByYearBorn(int yearBorn);

    long countByNameContaining(String keyword);

    // ==========================
    // Delete
    // ==========================
    void deleteByName(String name);

    long deleteByYearBorn(int yearBorn);

    // ==========================
    // JPQL
    // ==========================
    @Query("SELECT a FROM Author a WHERE a.name LIKE %:keyword%")
    List<Author> findByKeyword(String keyword);

    @Query("SELECT a FROM Author a WHERE a.yearBorn > :year")
    List<Author> findAuthorsBornAfter(int year);

    @Query("SELECT COUNT(a) FROM Author a")
    long countAuthors();

    @Query("SELECT MAX(a.yearBorn) FROM Author a")
    Integer getYoungestAuthorYear();

    @Query("SELECT MIN(a.yearBorn) FROM Author a")
    Integer getOldestAuthorYear();
}