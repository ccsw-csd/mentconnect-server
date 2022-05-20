package com.ccsw.mentconnect.questionary.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ccsw.mentconnect.user.dto.UserDto;

@Entity
@Table(name = "questionary")
public class QuestionaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "description", nullable = false)
	private String description;

	// preguntar
	@Column(name = "questions", nullable = false)
	private int Nquestions;
	
	// preguntar
	@Column(name = "patients", nullable = false)
	private int Npatients;

	@ManyToOne
	@JoinColumn(name = "username")
	private UserDto user;

	@Column(name = "createDate", nullable = false)
	private Date createDate;

	@Column(name = "lastEditDate", nullable = false)
	private Date lastEditDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNquestions() {
		return Nquestions;
	}

	public void setNquestions(int nquestions) {
		Nquestions = nquestions;
	}

	public int getNpatients() {
		return Npatients;
	}

	public void setNpatients(int npatients) {
		Npatients = npatients;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(Date lastEditDate) {
		this.lastEditDate = lastEditDate;
	}
	
	

}
