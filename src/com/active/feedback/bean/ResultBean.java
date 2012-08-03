package com.active.feedback.bean;

import java.util.List;

public class ResultBean {
	
	private int questionId;
	private List<Integer> questionDataId;
	private int questionType;
	private String answerValue;	
	
	
	
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	public String getAnswerValue() {
		return answerValue;
	}
	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}
	public ResultBean(int questionIds, List<Integer> questionDataId,
			int questionType, String answerValue) {
		
		this.questionId = questionIds;
		this.questionDataId = questionDataId;
		this.questionType = questionType;
		this.answerValue = answerValue;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionIds) {
		this.questionId = questionIds;
	}
	public List<Integer> getQuestionDataId() {
		return questionDataId;
	}
	public void setQuestionDataId(List<Integer> questionDataId) {
		this.questionDataId = questionDataId;
	}
	
		
	
	
	

}
