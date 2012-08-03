package com.active.feedback.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Team {
	
	private int id;
	private String name;
	private boolean selected;
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
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
