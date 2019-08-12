package application;

import static java.lang.System.exit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Scanner;

import entity.Employee;
import entity.Team;
import exception.ExceptionInvalidFileExtension;
import exception.ExceptionInvalidFileName;
import exception.ExceptionOptionInvalid;
import service.CompanyService;
import service.CompanyServiceImpl;
import util.EnumOpcoesMenu;

public class Main {

	public static void main(String[] args) throws Exception {
	
		Hashtable<Integer, ArrayList> hashFiles = null;
		Hashtable<Integer, ArrayList> hashPromotedEmployees = null;
		ArrayList<Team> arrTeams = null;
		
		Calendar toDay = Calendar.getInstance();
		int currentYear = toDay.get(Calendar.YEAR);
		
		CompanyService companyService = new CompanyServiceImpl();
		
		Scanner input = new Scanner(System.in);
		
		String optionn = showMenu();
		
			do {
				
				try {
					
				EnumOpcoesMenu enumOpcoesMenu = Enum.valueOf(EnumOpcoesMenu.class, optionn.toUpperCase());
			   switch (enumOpcoesMenu) {
			
			       case LOAD:
			    	   ArrayList arrFiles = new ArrayList<>();
			    	   System.out.println("Input name file 1 with extension: ");
			    	   arrFiles.add(input.next());
			    	   
			    	   System.out.println("Input name file 2 with extension: ");
			    	   arrFiles.add(input.next());
			    	   
			    	   //Recupera as informações dos arquivos
			    	   hashFiles = null;
			    	   hashFiles = companyService.readFiles(arrFiles);
			    	   
			    	   if(hashFiles != null) {
			    		   System.out.println("Files loaded!!!");			    		   
			    	   }
			           optionn = "DEFAULT";
			           break;
			       case ALLOCATE:
			           //Se os arquivos não forem carregados não deixa prosseguir
			    	   if(hashFiles == null) {
			    		   System.out.println("Sorry, files not loaded.");
			    	   } else {
			    		   arrTeams = companyService.allocateTeams(hashFiles);
		
			    		   //Exibe os times alocados
			    		   showAllocatedTeams(arrTeams);
			    	   }
			    	   
			    	   
			    	   optionn = "DEFAULT";
			           break;
			       case PROMOTE:
			    	   //Se os times não forem carregados não deixa prosseguir
			    	   if(arrTeams == null) {
			    		   System.out.println("Sorry, Teams aren't allocated.");
			    	   } else {
			    		   ArrayList<Employee> arrPromotedEmployees = null;
			    		   int countOfEmployees = 0;
			    		   System.out.println("Input the count of employees you will be promote: ");
			    		   countOfEmployees = input.nextInt();
			    		   
			    		   hashPromotedEmployees = companyService.promoteEmployee(arrTeams, countOfEmployees, currentYear);
			    		   
			    		   arrPromotedEmployees = hashPromotedEmployees.get(0);
			    		   arrTeams = hashPromotedEmployees.get(1);
			    		   
			    		   //Adicionamos 1 ano após ocorrer as promoções
			    		   currentYear ++;
			    		   
			    		   //Exibimos os empregados promovidos
			    		   showPromotedEmployees(arrPromotedEmployees);
			    		   
			    		   showAllocatedTeams(arrTeams);
			    	   }
			    	   
			    	   optionn = "DEFAULT";
			           break;
			       case BALANCE:
			    	   //Se os times não forem carregados não deixa prosseguir
			    	   if(arrTeams == null) {
			    		   System.out.println("Sorry, Teams aren't allocated.");
			    	   } else {
			    		  arrTeams = companyService.balanceTeams(arrTeams);
			    		  
			    		  
			    		  showAllocatedTeams(arrTeams);
			    	   }
			    	   
			    	   optionn = "DEFAULT";
			           break;
			       case EXIT:
			    	   System.out.println("Good Bye"); 
			    	   optionn = "EXIT";
			           exit(0);
			       case DEFAULT:
			    	   if(!optionn.equals("DEFAULT")) {
			    		   System.out.println("Sorry, please enter valid Option");   
			    	   }
			           
			           optionn = showMenu();
			           
			   }// End of switch statement
			
				} catch (IllegalArgumentException e) {
					ExceptionOptionInvalid e1 = new ExceptionOptionInvalid();
					System.out.println(e1.getMessage());
					optionn = showMenu();
				} catch (ExceptionInvalidFileExtension | ExceptionInvalidFileName e) {
					System.out.println(e.getMessage());
					optionn = showMenu();
				}
			} while (!optionn.equals("EXIT"));
			System.out.println("Thank you. Good Bye.");
		
	}

	public static String showMenu() {
	
	    String optionn = "";
	    Scanner keyboard = new Scanner(System.in);
	    System.out.println("           ");
	    System.out.println("Main Menu:");
	    System.out.println("--------------");
	    System.out.println("LOAD");
	    System.out.println("ALLOCATE");
	    System.out.println("PROMOTE");
	    System.out.println("BALANCE");
	    System.out.println("EXIT");
	    System.out.println("--------------");
	    System.out.println("Enter your choice:");
	    optionn = keyboard.next();
	    
	
	    return optionn;
	
	}
	
	public static void showAllocatedTeams(ArrayList<Team> pArrTeams) {
		System.out.println("           ");
		System.out.println("--------------");
		System.out.println("TEAMS");
		
		for (Team team : pArrTeams) {
			System.out.println("           ");
			System.out.println("--" + team.getName() + " Min. Maturity " + team.getMinMaturity() + " - Current Maturity " + team.getActualMaturity() + "--");
			
			
			for (Employee employee : team.gethashEmployee().values()) {
				System.out.println(employee.toString());
			}
			System.out.println("           ");
		}
	}
	
	public static void showPromotedEmployees(ArrayList<Employee> pArrPromotedEmployees) {
		System.out.println("           ");
		System.out.println("--------------");
		System.out.println("PROMOTED EMPLOYEES");
		
		for (Employee employee : pArrPromotedEmployees) {
			System.out.println(employee.getName() + " - From: " + (employee.getpLevel() - 1) + " - To: " + employee.getpLevel());
		}
		
		System.out.println("           ");
	}

}
