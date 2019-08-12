package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Team {
	
	private String name;
	private Integer minMaturity;
	private Integer actualMaturity = 0;
	
	private HashMap<Integer, Employee> hashEmployee;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMinMaturity() {
		return minMaturity;
	}
	public void setMinMaturity(Integer minMaturity) {
		this.minMaturity = minMaturity;
	}
	public Integer getActualMaturity() {
		return actualMaturity;
	}
	public void setActualMaturity(Integer actualMaturity) {
		this.actualMaturity = actualMaturity;
	}
	public HashMap<Integer, Employee> gethashEmployee() {
		return hashEmployee;
	}
	public void setHashEmployee(HashMap<Integer, Employee> pPashEmployee) {
		this.hashEmployee = pPashEmployee;
	}
	
	public Integer getDiferenceMaturity() {
		return actualMaturity - minMaturity;
	}
}
