package service;

import java.util.ArrayList;
import java.util.Hashtable;

import entity.Team;
import exception.ExceptionInvalidFileName;
import exception.ExceptionOptionInvalid;
import service.rn.RNAllocateEmployees;
import service.rn.RNBalanceTeams;
import service.rn.RNPromoteEmployee;
import service.rn.RNReadFiles;

public class CompanyServiceImpl implements  CompanyService{

	public Hashtable<Integer, ArrayList> readFiles(ArrayList<String> pArrFiles) throws ExceptionInvalidFileName, ExceptionOptionInvalid {
		return RNReadFiles.getInstancia().executar(pArrFiles);
	}

	public ArrayList<Team> allocateTeams(Hashtable<Integer, ArrayList> pFilesLoaded) {
		return RNAllocateEmployees.getInstancia().executar(pFilesLoaded);
	}

	public Hashtable<Integer, ArrayList> promoteEmployee(ArrayList<Team> pArrTeams, int pCountOfEmployees, int pCurrentYear) {
		return RNPromoteEmployee.getInstancia().executar(pArrTeams, pCountOfEmployees, pCurrentYear);
	}
	
	public ArrayList<Team> balanceTeams(ArrayList<Team> pArrTeam){
		return RNBalanceTeams.getInstancia().executar(pArrTeam);
	}

}
