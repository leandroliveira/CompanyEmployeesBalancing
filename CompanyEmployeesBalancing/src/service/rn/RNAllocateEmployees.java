package service.rn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Stack;

import entity.Employee;
import entity.Team;

public class RNAllocateEmployees {
	private static RNAllocateEmployees aRNAllocateEmployees;
	
	public static RNAllocateEmployees getInstancia() {  
		if(aRNAllocateEmployees == null) {
			return new RNAllocateEmployees();	
		} else {
			return aRNAllocateEmployees;
		}
	}
	
	public ArrayList<Team> executar(Hashtable<Integer, ArrayList> pFilesLoaded){
		
		ArrayList<Team> retorno = new ArrayList<Team>();
		ArrayList<Employee> employeesLoaded = new ArrayList<Employee>(pFilesLoaded.get(0));
		ArrayList<Team> teamsLoaded = new ArrayList<Team>(pFilesLoaded.get(1));
		
		Stack<Employee> stackEmployee = new Stack<Employee>();
		stackEmployee.addAll(employeesLoaded);
		
		
		for (Team team : teamsLoaded) {
			HashMap<Integer, Employee> hashTeamEmployee = new HashMap<Integer, Employee>();
			
			do {
				Employee employee = stackEmployee.pop();	
				
				team.setActualMaturity(team.getActualMaturity() + employee.getpLevel());

				hashTeamEmployee.put(employee.getName().hashCode(), employee);
				
				
			} while(team.getActualMaturity() <= team.getMinMaturity());
			
			team.setHashEmployee(hashTeamEmployee);
			
			retorno.add(team);
		}
		
		//TODO - Fazer o ballance caso alguma equipe nao atinja o minimo de maturidade
		return retorno;
	}
}
