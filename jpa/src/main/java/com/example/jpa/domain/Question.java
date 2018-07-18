package com.example.jpa.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question {

	@Id
	@GeneratedValue
	private Long questionNo;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;

	// private String writer;
	private String title;
	private String contents;
	
	
	private LocalDateTime createDate;



	@Override
	public String toString() {
		return "Question [questionNo=" + questionNo + ", writer=" + writer + ", title=" + title + ", contents="
				+ contents + ", createDate=" + createDate + "]";
	}

	public Long getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Long questionNo) {
		this.questionNo = questionNo;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getCreateDate() {
		return this.createDate == null ? "" : createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	

}
