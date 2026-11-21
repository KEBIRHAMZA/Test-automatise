package org.mql.spring.biblio.controllers;

import org.mql.spring.biblio.models.Author;
import org.mql.spring.biblio.services.BiblioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Conventions d'une API Rest :
 * GET		: /authors		: On recupere une Liste/Tableau d'auteurs
 * GET 		: /authors/{id}	: On recuperer un auteur par id : id etant fournie comme variable de chemin (path variable)
 * POST		: /authors		: Inserer un auteur (recu dans le body : Request Body)
 * PUT		: /authors		: Modifier un auteur (recu dans le body :Request Body)
 * DELETE	: /authors/{id}	: On supprime un auteur par son id (Path Variable)
 * Si on veut envoyer d'autre infos a l'api Rest on pourra utiliser des parametre de requetes : Request Params
 * Une recherche par nom a titre d'exemple pourait se faire comme suit :
 * GET		: /authors?name=James 
 * */

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
	private BiblioService service;
	
	public AuthorRestController(BiblioService service) {
		this.service = service;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthor(@PathVariable int id) {
		// => Entite de responce constituee de 2 informations : Status et l'objet JSON 
		Author author = service.getAuthorById(id);
		if(author != null) {
			return ResponseEntity.ok().body(author); // j'ai envoyer le status et body (objet JSON)
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
