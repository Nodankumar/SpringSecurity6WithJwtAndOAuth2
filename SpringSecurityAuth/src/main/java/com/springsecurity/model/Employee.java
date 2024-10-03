package com.springsecurity.model;

public class Employee {

	private int empId;
	private String empName;
	private double salary;
		
	public Employee() {
		super();
	}

	public Employee(int empId, String empName, double salary) {
		super();
		this.setEmpId(empId);
		this.setEmpName(empName);
		this.setSalary(salary);
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
}
