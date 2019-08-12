package util;

import java.util.Comparator;

import entity.Employee;

public class EmployeeComparatorByRanking implements Comparator<Employee>{
	@Override
	public int compare(Employee o1, Employee o2) {
		return o2.getPoints().compareTo(o1.getPoints());
	}
}
