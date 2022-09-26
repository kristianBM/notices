package com.surfersolution.notices.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.surfersolution.notices.domain.Notice;
import com.surfersolution.notices.dto.NoticeDTO;
import com.surfersolution.notices.services.NoticeService;

@RestController
@RequestMapping(value = "/notices")
public class NoticeResource {

	@Autowired
	private NoticeService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Notice>> findAll() {
		List<Notice> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Notice> findById(@PathVariable Long id) {
		Notice obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	@RequestMapping(value = "/create" ,method = RequestMethod.POST)
	public ResponseEntity<Notice> insert(@RequestBody Notice obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@Transactional
	@RequestMapping(value= "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Notice> update (@PathVariable Long id, @RequestBody Notice obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@Transactional(readOnly = true)
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<NoticeDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Notice> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<NoticeDTO> listDto = list.map(obj -> new NoticeDTO(obj));
		return ResponseEntity.ok().body(listDto);

	}
}
