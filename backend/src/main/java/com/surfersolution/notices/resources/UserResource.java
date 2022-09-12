package com.surfersolution.notices.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.surfersolution.notices.domain.User;
import com.surfersolution.notices.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<User>> findAll(Pageable pageable) {
		Page<User> list = service.findAll(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@Transactional(readOnly = true)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}
	
	@Transactional
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@Transactional
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> update (@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
