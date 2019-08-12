package util;

import java.util.Comparator;

import entity.Employee;

public class EmployeeComparatorByPLevel implements Comparator<Employee>{
	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getpLevel().compareTo(o2.getpLevel());
	}
}
