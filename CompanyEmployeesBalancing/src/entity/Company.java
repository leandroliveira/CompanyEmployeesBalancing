package entity;

import java.util.ArrayList;

public class Company {
	private String name;
	
	private ArrayList<Team> arrTeams;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Team> getArrTeams() {
		return arrTeams;
	}

	public void setArrTeams(ArrayList<Team> arrTeams) {
		this.arrTeams = arrTeams;
	}
	
	
}
