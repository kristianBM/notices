package com.surfersolution.notices.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.surfersolution.notices.domain.User;
import com.surfersolution.notices.repositories.UserRepository;
import com.surfersolution.notices.services.exceptions.DatabaseException;
import com.surfersolution.notices.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	public User user;
	
	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder pe;

	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);

		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public User insert(User obj) {
		obj.setId(null);
		obj.setPassword(pe.encode(obj.getPassword()));
		return userRepository.save(obj);
	}

	public User update(Long id, User obj) {

		User entity = userRepository.getById(id);
		updateData(entity, obj);
		return userRepository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());

	}

	public void delete(Long id) {
		try {
			userRepository.getById(id);
			userRepository.deleteById(id);
		} catch (IllegalStateException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return userRepository.findAll(pageRequest);
	}
	
}
