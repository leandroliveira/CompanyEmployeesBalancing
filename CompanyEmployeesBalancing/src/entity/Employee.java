package entity;

public class Employee {
	
	private String name;
	private Integer pLevel;
	private int birthYear;
	private int admissionYear;
	private int lastProgressionYear;
	private Integer points;
	
	public Employee() {
		super();
		this.points = 0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getpLevel() {
		return pLevel;
	}
	public void setpLevel(Integer pLevel) {
		this.pLevel = pLevel;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public int getAdmissionYear() {
		return admissionYear;
	}
	public void setAdmissionYear(int admissionYear) {
		this.admissionYear = admissionYear;
	}
	public int getLastProgressionYear() {
		return lastProgressionYear;
	}
	public void setLastProgressionYear(int lastProgressionYear) {
		this.lastProgressionYear = lastProgressionYear;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	@Override
	public String toString() {
		return name + " - " + pLevel ;
	}
	
	
}
