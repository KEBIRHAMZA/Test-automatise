package org.mql.spring.biblio.services;

import java.util.List;
import java.util.Optional;

import org.mql.spring.biblio.models.Author;
import org.mql.spring.biblio.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class BiblioServiceDefault implements BiblioService {

	private final AuthorRepository authorRepository;

	public BiblioServiceDefault(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public Author getAuthorById(int id) {
		Optional<Author> author = authorRepository.findById(id);
		return author.orElse(null);
	}

	@Override
	public List<Author> getAuthorByName(String name) {
		return authorRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public boolean addAuthor(Author author) {
		if (author == null || authorRepository.existsById(author.getId())) {
			return false;
		}
		authorRepository.save(author);
		return true;
	}

	@Override
	public boolean saveAuthor(Author author) {
		if (author == null) {
			return false;
		}
		authorRepository.save(author);
		return true;
	}

	@Override
	public Author removeAuthor(int id) {
		Optional<Author> author = authorRepository.findById(id);
		if (author.isPresent()) {
			authorRepository.deleteById(id);
			return author.get();
		}
		return null;
	}
}