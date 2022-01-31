package com.surfersolution.notices.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.surfersolution.notices.domain.Notice;
import com.surfersolution.notices.repositories.NoticeRepository;
import com.surfersolution.notices.services.exceptions.DatabaseException;
import com.surfersolution.notices.services.exceptions.ResourceNotFoundException;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private EmailService emailService;

	public Notice findById(Long id) {
		Optional<Notice> obj = noticeRepository.findById(id);

		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Notice> findAll() {
		return noticeRepository.findAll();
	}

	public Notice insert(Notice obj) {
		obj.setId(null);
		obj.setTitle(obj.getTitle());
		obj.setNotice(obj.getNotice());
		obj.setPostDate(new Date());
		obj = noticeRepository.save(obj);
		emailService.sendNoticeHtmlEmail(obj);
		return obj;
	}

	public Notice update(Long id, Notice obj) {

		Notice entity = noticeRepository.getById(id);
		updateData(entity, obj);
		return noticeRepository.save(entity);
	}

	private void updateData(Notice entity, Notice obj) {

		entity.setTitle(obj.getTitle());
		entity.setNotice(obj.getNotice());
		
	}

	public void delete(Long id) {
		try {
			noticeRepository.deleteById(id);
		} catch(IllegalStateException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	public Page<Notice> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return noticeRepository.findAll(pageRequest);
	}
}
