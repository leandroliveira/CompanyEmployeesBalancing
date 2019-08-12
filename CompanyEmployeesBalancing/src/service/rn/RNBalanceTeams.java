package service.rn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import entity.Employee;
import entity.Team;
import util.EmployeeComparatorByPLevel;

public class RNBalanceTeams {

	private static RNBalanceTeams aRNBalanceTeams;
	private int avgTotalMaturity;
	
	public static RNBalanceTeams getInstancia() {  
		if(aRNBalanceTeams == null) {
			return new RNBalanceTeams();	
		} else {
			return aRNBalanceTeams;
		}
	}
	
	public ArrayList<Team> executar(ArrayList<Team> pArrTeam){
		Team teamFittest = null;
		Stack<Team> stackTeam = null;
		boolean empoyeeChanged = false;
		
		do {
			//Get ordered teams by diference of maturity
			stackTeam = this.returnOrderedTeams(pArrTeam);
			
			//Get fittest team to receive new employees
			teamFittest = stackTeam.lastElement();
			
			if(teamFittest != null) {
				empoyeeChanged = this.chooseEmployee(stackTeam);
			}
		} while(teamFittest != null && empoyeeChanged);
		
		return pArrTeam;
		
	}

	//Calculate total diference in all teams
	private Stack<Team> returnOrderedTeams(ArrayList<Team> arrTeam) {
		Stack<Team> stackTeams = new Stack<Team>();
		
		for (Team team : arrTeam) {
			avgTotalMaturity = avgTotalMaturity + (team.getActualMaturity() - team.getMinMaturity());
			
			stackTeams.push(team);
		}
		
		Comparator<Team> comparator = (o1, o2)->(o1.getDiferenceMaturity().compareTo(o2.getDiferenceMaturity()));
		
		stackTeams.sort(comparator);
		
		//Average of total teams diference maturity
		avgTotalMaturity = avgTotalMaturity / arrTeam.size();
		
		return stackTeams;
	}
	
	//Choose the employee that will change team 
	private boolean chooseEmployee (Stack<Team> stackTeam) {
		
		Team teamTo = stackTeam.firstElement();
		Team teamFrom = null;
		
		int countPointsToReceive = avgTotalMaturity - (teamTo.getActualMaturity() - teamTo.getMinMaturity());
		int hashCodeNameSelected = 0;
		
		while (!stackTeam.isEmpty() && hashCodeNameSelected == 0) {
			teamFrom = stackTeam.pop();
			
			ArrayList<Employee> arrListEmployeeFrom = new ArrayList<Employee>(teamFrom.gethashEmployee().values());
			arrListEmployeeFrom.sort(Comparator.comparingInt(Employee::getpLevel));
			
			for (Employee employee : arrListEmployeeFrom) {
				
				if(teamFrom.getDiferenceMaturity() - employee.getpLevel() >= avgTotalMaturity) {
					hashCodeNameSelected = employee.getName().hashCode();	
					break;
				} else if(employee.getpLevel() > countPointsToReceive) {
					break;
				}
			}
		}
		
		if(hashCodeNameSelected != 0) {
			this.swapEmployye(teamFrom, teamTo, hashCodeNameSelected);
			return true;
		} else {
			return false;
		}
		
	}

	//Update Teams ActualMaturity and list of Employees
	private void swapEmployye(Team teamFrom, Team teamTo, int hashCodeNameSelected) {
		Employee employeeRemoved = teamFrom.gethashEmployee().remove(hashCodeNameSelected);
		teamFrom.setActualMaturity(teamFrom.getActualMaturity() - employeeRemoved.getpLevel());
		
		teamTo.gethashEmployee().put(hashCodeNameSelected, employeeRemoved);			
		teamTo.setActualMaturity(teamTo.getActualMaturity() + employeeRemoved.getpLevel());
	}
	
	
	
}
