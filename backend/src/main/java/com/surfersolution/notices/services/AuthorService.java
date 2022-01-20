package com.surfersolution.notices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.surfersolution.notices.domain.Author;
import com.surfersolution.notices.repositories.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Optional<Author> findById(Long id) {
	Optional<Author> obj = authorRepository.findById(id);
		
		return obj;
	}

	public List<Author> findAll(){
		return authorRepository.findAll();
	}
	
	public Author insert(Author obj) {
		return authorRepository.save(obj);
	}
	
	public Author update(Long id, Author obj) {

			Author entity = authorRepository.getById(id);
			updateData(entity, obj);
			return authorRepository.save(entity);
	}

	private void updateData(Author entity, Author obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		
	}
	
	public void delete(Long id) {
		authorRepository.deleteById(id);
	}
	
	public Page<Author> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return authorRepository.findAll(pageRequest);
	}

}
