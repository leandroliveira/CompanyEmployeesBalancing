package service;

import java.util.ArrayList;
import java.util.Hashtable;

import entity.Employee;
import entity.Team;
import exception.ExceptionInvalidFileName;
import exception.ExceptionOptionInvalid;

public interface CompanyService {

	public Hashtable<Integer, ArrayList> readFiles(ArrayList<String> pArrFiles) throws ExceptionInvalidFileName, ExceptionOptionInvalid;
	
	public ArrayList<Team> allocateTeams(Hashtable<Integer, ArrayList> filesLoaded);
	
	public Hashtable<Integer, ArrayList> promoteEmployee(ArrayList<Team> pArrTeam, int pCountOfEmployees, int pCurrentYear);
	
	public ArrayList<Team> balanceTeams(ArrayList<Team> pArrTeam);
}
