package com.active.feedback.dao;

import java.util.List;
import java.util.Map;

import com.active.feedback.entities.Question;
import com.active.feedback.entities.Survey;
import com.active.feedback.entities.Survey2Question;
import com.active.feedback.entities.Survey2Team;
import com.active.feedback.entities.SurveyReport;
import com.active.feedback.entities.Team;

public interface SurveyDao {
	
	public void add(Survey survey);
	
	public void delete(Survey survey);
	
	public void update(Survey survey);
	
	public Survey getSurveyById (int id);
	
	public List<Survey> getAllSurveys();
	
	public void addSurvey2Question (Survey2Question s2q);
	
	public List<Question> getQuestionsBySurveyId (int id);
	
	public void addSurvey2Team (Survey2Team s2t);
	
	public List<Survey> getAllSurveysByUserId(int userId);
	
	public List<Team> getTeamsBySurveyId (int id);
	
	public void deleteTeamsBySurveyId (int id);
	
	public List<SurveyReport> getSurveyReportByIds(int survey_id, int team_id, int user_id);
	
	public Map<String, String> getSurveyInfoForEmailById(int id);
	
	public void deleteQuestionBySurveyId(int survey_id, int q_id);
	
}
