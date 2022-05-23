package com.ccsw.mentconnect.questionnaire.model;

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
import com.ccsw.mentconnect.user.model.UserEntity;

@Entity
@Table(name = "questionnaire")
public class QuestionnaireEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "description", nullable = false)
	private String description;

	// preguntar
	@Column(name = "questions", nullable = false)
	private Integer questionsNumber;
	
	// preguntar
	@Column(name = "patients", nullable = false)
	private Integer patientsNumber;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Column(name = "last_edit_date", nullable = false)
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
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
