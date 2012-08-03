package com.active.feedback.dao;

import java.util.List;

import com.active.feedback.entities.QuestionType;

public interface QuestionTypeDao {
	
	public List<QuestionType> getAllQuestionType();
	
	public QuestionType getQuestionTypeById(int qt_id);
}
