package service.rn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;

import entity.Employee;
import entity.Team;
import util.Constant;
import util.EmployeeComparatorByRanking;

public class RNPromoteEmployee {

private static RNPromoteEmployee aRNPromoteEmployee;
	
	public static RNPromoteEmployee getInstancia() {  
		if(aRNPromoteEmployee == null) {
			return new RNPromoteEmployee();	
		} else {
			return aRNPromoteEmployee;
		}
	}
	
	public Hashtable<Integer, ArrayList> executar(ArrayList<Team> pArrTeams, int pCountOfEmployees, int pCurrentYear){
		Hashtable<Integer, ArrayList> hashReturn = new Hashtable<Integer, ArrayList>();
		boolean isAble = true;

		ArrayList arrPromotedEmployees = new ArrayList<>();
		ArrayList<Employee> arrEmployee = new ArrayList<Employee>();
		
		//Criteria Restriction
		for (Team team : pArrTeams) {
			for (Employee employee : team.gethashEmployee().values()) {
				
				isAble = true;
				//Company time Minimum 1 year or pLevel is 5(because this is the maximum level)
				if(pCurrentYear - employee.getAdmissionYear() < Constant.COMPANY_TIME ||
						employee.getpLevel() == Constant.EMPLOYEE_MAXIMUM_LEVEL) {
					isAble = false;
				
				//Time without progression Minimum 2 years, if level is 4.
				} else if(employee.getpLevel() == 4 &&
						pCurrentYear - employee.getLastProgressionYear()  <= Constant.COMPANY_TIME_MIN_WITHOUT_PROGRESSION_TIME) {
					isAble = false;
				}
				
				if(isAble) {
					
					employee.setPoints(this.calculatePoints(employee, pCurrentYear));
										
					arrEmployee.add(employee);
				}
			}
		}
		
		//Sort by points of employee
		Collections.sort(arrEmployee, new EmployeeComparatorByRanking());
		
		for(int i=0; !arrEmployee.isEmpty() && i < pCountOfEmployees; i++) {
			//Get count of employees to promove
			arrPromotedEmployees.add(arrEmployee.get(i));
			
			for (Team team : pArrTeams) {
				HashMap<Integer, Employee> hashSearchEmployee = team.gethashEmployee();
				
				if(hashSearchEmployee.containsKey(arrEmployee.get(i).getName().hashCode())) {
					this.promoteEmployee(hashSearchEmployee, arrEmployee.get(i), pCurrentYear);
					
					//Add in a current team maturity
					team.setActualMaturity(team.getActualMaturity() + 1);
				}
			}
		}
		
		hashReturn.put(0, arrPromotedEmployees);
		hashReturn.put(1, pArrTeams);
		
		return hashReturn;
	}
	
	//Set values to promote employee
	private void promoteEmployee (HashMap<Integer, Employee> pHashSearchEmployee, Employee pEmployee, int pCurrentYear) {
		Employee employee = pHashSearchEmployee.get(pEmployee.getName().hashCode());
		employee.setpLevel(employee.getpLevel() + 1);
		employee.setLastProgressionYear(pCurrentYear);
	}
	
	//Calculate points to each employee
	private Integer calculatePoints(Employee pEmployee, int pCurrentYear) {
		int points = 0;
		int companyPointsPerTime = 0;
		int pointsWithoutProgression = 0;
		int agePoint = 0;
		/*
		Criteria Criteria Weight
		Company time 2 points per year
		Time without progression 3 points per year
		Age 1 point per 5 years old
		*/
		companyPointsPerTime = (pCurrentYear - pEmployee.getAdmissionYear()) * Constant.COUNT_FACTOR_COMPANY_TIME;
		pointsWithoutProgression = (pCurrentYear - pEmployee.getLastProgressionYear()) * Constant.COUNT_FACTOR_WITHOUT_PROGRESSION_TIME;
		agePoint = (pCurrentYear - pEmployee.getBirthYear()) / Constant.COUNT_FACTOR_YEARS;
		
		points = companyPointsPerTime + pointsWithoutProgression + agePoint;
				
		return points;
	}
}
