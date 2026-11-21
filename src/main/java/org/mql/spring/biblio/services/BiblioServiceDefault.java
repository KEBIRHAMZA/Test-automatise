package org.mql.spring.biblio.services;

import java.util.Optional;

import org.mql.spring.biblio.models.Author;
import org.mql.spring.biblio.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiblioServiceDefault implements BiblioService{
	
	@Autowired
	private AuthorRepository authorRepository;
	
	
	public BiblioServiceDefault() {
		
	}

	@Override
	public Author getAuthorById(int id) {
		Optional<Author> author = authorRepository.findById(id);
		if(author.isPresent()) return author.get();
		return null;
	}

}
