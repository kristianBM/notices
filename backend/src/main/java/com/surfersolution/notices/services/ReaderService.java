package com.surfersolution.notices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.surfersolution.notices.domain.Reader;
import com.surfersolution.notices.repositories.ReaderRepository;
import com.surfersolution.notices.services.exceptions.DatabaseException;
import com.surfersolution.notices.services.exceptions.ResourceNotFoundException;

@Service
public class ReaderService {

	@Autowired
	public Reader reader;
	
	@Autowired
	private ReaderRepository readerRepository;

	public Reader findById(Long id) {
		Optional<Reader> obj = readerRepository.findById(id);

		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Reader> findAll() {
		return readerRepository.findAll();
	}

	public Reader insert(Reader obj) {
		reader.getEmails().add(obj.getEmail());

		return readerRepository.save(obj);
	}

	public Reader update(Long id, Reader obj) {

		Reader entity = readerRepository.getById(id);
		updateData(entity, obj);
		return readerRepository.save(entity);
	}

	private void updateData(Reader entity, Reader obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());

	}

	public void delete(Long id) {
		try {
			Reader obj = readerRepository.getById(id);
			reader.getEmails().remove(obj.getEmail());
			readerRepository.deleteById(id);
		} catch (IllegalStateException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Page<Reader> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return readerRepository.findAll(pageRequest);
	}
	
}
