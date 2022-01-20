package com.surfersolution.notices.dto;

import java.io.Serializable;

import com.surfersolution.notices.domain.Notice;

public class NoticeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String notice;
	
	public NoticeDTO() {
	}

	public NoticeDTO(Notice obj) {
	id = obj.getId();
	title = obj.getTitle();
	notice = obj.getNotice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	
}
