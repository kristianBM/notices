package com.surfersolution.notices.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_notices")
public class Notice implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String notice;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date postDate;
	
// Lista de leitores para envio de newsletter(a implementar)
//	@ElementCollection
//	@CollectionTable(name = "readers")
//	private Set<Reader>readers = new HashSet<>();
	
	public Notice() {
		
	}

	public Notice(Long id, String title, String notice, Author author, Date postDate) {
		this.id = id;
		this.title = title;
		this.notice = notice;
		this.author = author;
		this.postDate = postDate;
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
//	public Set<Reader> getReaders() {
//		return readers;
//	}
//
//	public void setReaders(Set<Reader> readers) {
//		this.readers = readers;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notice other = (Notice) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
