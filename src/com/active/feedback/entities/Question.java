package com.active.feedback.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question {
	
	private int id;
	private String name;
	private QuestionType qt;
	private List<QuestionData> qdList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public QuestionType getQt() {
		return qt;
	}
	public void setQt(QuestionType qt) {
		this.qt = qt;
	}
	public List<QuestionData> getQdList() {
		return qdList;
	}
	public void setQdList(List<QuestionData> qdList) {
		this.qdList = qdList;
	}
}
