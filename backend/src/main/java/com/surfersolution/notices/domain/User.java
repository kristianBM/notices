package com.surfersolution.notices.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.surfersolution.notices.domain.enums.Profile;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String name;
	private String password;
	private Integer profile;
	
	public User() {
	}
	
	public User(Long id, String email, String name, String password, Profile profile) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.profile = (profile == null) ? null : profile.getCod();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return Profile.toEnum(profile);
	}

	public void setProfile(Profile profile) {
		this.profile = profile.getCod();
	}

}
