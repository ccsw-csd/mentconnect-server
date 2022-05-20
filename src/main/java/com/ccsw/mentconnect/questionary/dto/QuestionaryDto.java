package com.ccsw.mentconnect.questionary.dto;

import java.util.Date;

import com.ccsw.mentconnect.user.dto.UserDto;

public class QuestionaryDto {
	
	private Long id;
	
	private String description;
	
	//preguntar
	private int Nquestions;
	//preguntar
	private int Npatients;
	
	private UserDto user;
	
	private Date createDate;
	
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
