package com.example.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {

	@Id
	@GeneratedValue
	private Long questionNo;

	private String writer;
	private String title;
	private String contents;
	
	
	
	@Override
	public String toString() {
		return "Question [questionNo=" + questionNo + ", writer=" + writer + ", title=" + title + ", contents="
				+ contents + "]";
	}
	public Long getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(Long questionNo) {
		this.questionNo = questionNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
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
	
	

}
