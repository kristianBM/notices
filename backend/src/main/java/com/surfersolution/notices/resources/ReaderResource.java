package com.surfersolution.notices.resources;

import java.net.URI;
import java.util.List;

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

import com.surfersolution.notices.domain.Reader;
import com.surfersolution.notices.dto.ReaderDTO;
import com.surfersolution.notices.services.ReaderService;

@RestController
@RequestMapping(value = "/api/v1/readers")
public class ReaderResource {

	@Autowired
	private ReaderService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Reader>> findAll() {
		List<Reader> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Reader> findById(@PathVariable Long id) {
		Reader obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Reader> insert(@RequestBody Reader obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Reader> update (@PathVariable Long id, @RequestBody Reader obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@Transactional(readOnly = true)
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ReaderDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Reader> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ReaderDTO> listDto = list.map(obj -> new ReaderDTO(obj));
		return ResponseEntity.ok().body(listDto);

	}
}
