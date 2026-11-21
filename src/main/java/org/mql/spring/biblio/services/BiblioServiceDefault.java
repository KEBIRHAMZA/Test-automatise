package org.mql.spring.biblio.services;

import org.mql.spring.biblio.models.Author;
import org.mql.spring.biblio.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class BiblioServiceDefault implements BiblioService{
	
	private AuthorRepository authorRepository;
	
	
	public BiblioServiceDefault(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
		
	}

	@Override
	public Author getAuthorById(int id) {
		
		return null;
	}


}
