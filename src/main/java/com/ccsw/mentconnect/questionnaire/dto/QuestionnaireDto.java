package com.ccsw.mentconnect.questionnaire.dto;

import java.util.Date;

import com.ccsw.mentconnect.user.dto.UserDto;

public class QuestionnaireDto {
	
	private Long id;
	
	private String description;
	
	//preguntar
	private Integer questionsNumber;
	//preguntar
	private Integer patientsNumber;
	
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

	public Integer getQuestionsNumber() {
		return questionsNumber;
	}

	public void setQuestionsNumber(Integer questionsNumber) {
		this.questionsNumber = questionsNumber;
	}

	public Integer getPatientsNumber() {
		return patientsNumber;
	}

	public void setPatientsNumber(Integer patientsNumber) {
		this.patientsNumber = patientsNumber;
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
