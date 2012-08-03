package com.active.feedback.entities;

import java.util.List;

public class SurveyReport {

	private int q_id;
	private String q_name;
	private String q_type;
	private List<String> answerList;
	private List<QuestionData> qdList;

	public int getQ_id() {
		return q_id;
	}
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}
	public String getQ_type() {
		return q_type;
	}
	public void setQ_type(String q_type) {
		this.q_type = q_type;
	}
	public List<String> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<String> answerList) {
		this.answerList = answerList;
	}
	public List<QuestionData> getQdList() {
		return qdList;
	}
	public void setQdList(List<QuestionData> qdList) {
		this.qdList = qdList;
	}
	public String getQ_name() {
		return q_name;
	}
	public void setQ_name(String q_name) {
		this.q_name = q_name;
	}
}
