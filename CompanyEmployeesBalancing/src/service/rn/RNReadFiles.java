package service.rn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;

import entity.Employee;
import entity.Team;
import exception.ExceptionInvalidFileExtension;
import exception.ExceptionInvalidFileName;
import exception.ExceptionOptionInvalid;

public class RNReadFiles {
	private static RNReadFiles aRNReadFiles;
	private static FileReader aArq;
	
	public static RNReadFiles getInstancia() {
		
		if(aRNReadFiles == null) {
			return new RNReadFiles();	
		} else {
			return aRNReadFiles;
		}
		
	}

	public Hashtable<Integer, ArrayList> executar(ArrayList<String> pArrFiles) throws ExceptionOptionInvalid, ExceptionInvalidFileName {
		Hashtable<Integer, ArrayList> response = new Hashtable<Integer, ArrayList>();
		
		ArrayList<Employee> arrEmployee = new ArrayList<Employee>();
		ArrayList<Team> arrTeam = new ArrayList<Team>();
		
		try {
			
		  for (String nmFile : pArrFiles) {
			  
			String nmFileAux = nmFile.split("\\.")[0];
			String extensionFile = nmFile.split("\\.")[1];
			
			if(!extensionFile.equals("txt") && !extensionFile.equals("csv")) {
				throw new ExceptionInvalidFileExtension();
			}
			
			switch (nmFileAux) {
			 	case "employees":
			 		arrEmployee = this.readEmployees(nmFileAux+ "." + extensionFile);
			 		break;
			 	case "team":
			 		arrTeam = this.readTeam(nmFileAux + "." + extensionFile);
			 		break;

			 	default:
			 		throw new ExceptionInvalidFileName();
			 		
			}
		  }

		  response.put(0, arrEmployee);
		  response.put(1, arrTeam);
		  
	    } catch (IOException e) {
	    	response = null;
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	
		return response;
	}
/*	
	private Method returnEntity(String nmFile) throws NumberFormatException, IOException {
		Method metodoCarga = null;
		if(nmFile.split("\\.")[0].equals("employees")) {
			metodoCarga = this.getClass().getMethod("", );
		  } else if(nmFile.split("\\.")[0].equals("team")) {
			  metodoCarga = this.getClass().getMethod("", );
		  }
		return metodoCarga;
	}
*/
	/**
	 * - Lê o arquivo dos empregados
	 * @param nmCaminhoArquivos
	 * @return
	 * @throws NumberFormatException, IOException
	 */
	private ArrayList<Employee> readEmployees(String nmFile)
			throws NumberFormatException, IOException {
	  ArrayList<Employee> arrEmployee = new ArrayList<Employee>();
	  String lineEmployee[] = null;
	  String line = "";
	  BufferedReader lerArq = readFile("c:/temp/" , nmFile);
	
	  lineEmployee = lerArq.readLine().split(","); // lê a primeira linha
      // a variável "linha" recebe o valor "null" quando o processo
      // de repetição atingir o final do arquivo texto
      while (lineEmployee != null) {
        
        line = lerArq.readLine();
        
        if(line != null) {
	        lineEmployee = line.split(",");
        } else {
        	break;
        }
        
        arrEmployee.add(this.createEmploye(lineEmployee));
      }
      this.aArq.close();
		
	  return arrEmployee;
	}

	private Employee createEmploye(String[] lineEmployee) {
		Employee employee;
		employee = new Employee();
		employee.setName(lineEmployee[0]);
		employee.setpLevel(Integer.parseInt(lineEmployee[1]));
		employee.setBirthYear(Integer.parseInt(lineEmployee[2]));
		employee.setAdmissionYear(Integer.parseInt(lineEmployee[3]));
		employee.setLastProgressionYear(Integer.parseInt(lineEmployee[4]));
		return employee;
	}
	
	/**
	 * - Lê o arquivo dos Times
	 * @param nmCaminhoArquivos
	 * @return
	 * @throws NumberFormatException, IOException
	 */
	private ArrayList<Team> readTeam(String nmFile)
			throws NumberFormatException, IOException {
	  ArrayList<Team> arrTeam = new ArrayList<Team>();
	  String lineTeam[] = null;
	  String line = "";
	  BufferedReader lerArq =  readFile("c:/temp/" , nmFile);
	
	  lineTeam = lerArq.readLine().split(","); // lê a primeira linha
      // a variável "linha" recebe o valor "null" quando o processo
      // de repetição atingir o final do arquivo texto
      while (lineTeam != null) {
        line = lerArq.readLine();
        
        if(line != null) {
	        lineTeam = line.split(",");
        } else {
        	break;
        }
        
		arrTeam.add(this.createTeam(lineTeam));
      }
      this.aArq.close();
		
	  return arrTeam;
	}

	private Team createTeam(String[] lineTeam) {
		Team team = new Team();
		team.setName(lineTeam[0]);
		team.setMinMaturity(Integer.parseInt(lineTeam[1]));
		return team;
	}

	/**
	 * @param nmCaminhoArquivos - caminho da pasta onde contem o arquivo 
	 * @param nmArquivo - nome do arquivo a ser lido
	 * @return
	 * @throws FileNotFoundException
	 */
	private BufferedReader readFile(String nmPathFile, String nmFile) throws FileNotFoundException, IOException {
		this.aArq = new FileReader(nmPathFile+ nmFile);
		BufferedReader lerArq = new BufferedReader(this.aArq);
		
		return lerArq;
	}
}
