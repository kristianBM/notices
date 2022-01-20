package com.surfersolution.notices.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.surfersolution.notices.domain.Author;
import com.surfersolution.notices.dto.AuthorDTO;
import com.surfersolution.notices.services.AuthorService;

@RestController
@RequestMapping(value = "/api/v1/authors")
public class AuthorResource {

	@Autowired
	private AuthorService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Author>> findAll() {
		List<Author> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Author>> findById(@PathVariable Long id) {
		Optional<Author> obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Author> insert(@RequestBody Author obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Author> update (@PathVariable Long id, @RequestBody Author obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@Transactional(readOnly = true)
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<AuthorDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Author> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<AuthorDTO> listDto = list.map(obj -> new AuthorDTO(obj));
		return ResponseEntity.ok().body(listDto);

	}
}
