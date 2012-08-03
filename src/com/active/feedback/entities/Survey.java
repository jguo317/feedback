package com.active.feedback.entities;
import java.util.Date;

public class Survey {
	
	private int id;
	private int createBy;
	private Date startDate;
	private Date endDate;
	private Date timeStamp;	
	private String title;
	private int memberSurvey;
	
	
	public Survey() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date e) {
		this.endDate = e;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timestamp) {
		this.timeStamp = timestamp;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public int getMemberSurvey() {
		return memberSurvey;
	}
	public void setMemberSurvey(int memberSurvey) {
		this.memberSurvey = memberSurvey;
	}
}
