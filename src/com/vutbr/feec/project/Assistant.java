package com.vutbr.feec.project;

import com.vutbr.feec.project.Employee;

public class Assistant extends Employee {
	static final int SALARY_PER_HOUR = 150;
	static final String profession = "Assistant";
	private boolean ill = false;
	private double salary;
	private double maxHours;
	private boolean administrationWork = false;
	private double hoursA = 0;

	public Assistant(Integer id, String name, String lastName) {
		super(id, name, lastName);
	}

	// return string with type of work and how many hours do on that job
	public String workJobs() {
		if (this.administrationWork == true) {
			return "administrative work with " + df.format(this.getHoursA()) + " hours";
		} else {
			return "nothing";
		}
	}

	// for administration work
	@Override
	public String showEmployeeData() {
		this.setSalary();
		return "name: " + this.getName() + " " + this.getLastName() + " working " + df.format(this.getHours())
				+ " hours, salary is " + df.format(this.getSalary()) + " and is not ill";
	}

	@Override
	public String toString() {
		String[] stringHelp = { this.getId().toString(), this.getName(), this.getLastName(), profession };
		return String.format(format, stringHelp) + " working on " + this.workJobs();
	}

	@Override
	public String toDatabase() {
		return profession + ";" + this.getId() + ";" + this.getName() + ";" + this.getLastName() + ";" + this.getHours()
				+ ";" + this.getMaxHours() + ";" + this.getHoursA() + ";" + this.isAdministrationWork() + ";"
				+ this.isIll() + "\n";
	}

	@Override
	public String getProfession() {
		return profession;
	}

	public void myProff() {
		System.out.println(profession);
	}

	@Override
	public boolean isIll() {
		return ill;
	}

	@Override
	public void setIll(boolean ill) {
		this.ill = ill;
	}

	@Override
	public double getSalary() {
		return salary;
	}

	@Override
	public void setSalary() {
		if (this.isIll() == false && this.getHours() != 0) {
			this.salary = this.getHours() * SALARY_PER_HOUR;
		} else {
			this.salary = 500;
		}
	}

	@Override
	public double getMaxHours() {
		return maxHours;
	}

	@Override
	public void setMaxHours(double maxHours) {
		if (maxHours <= 744) {
			this.maxHours = maxHours;
		} else {
			System.out.println("enter number under 745");
		}
	}

	@Override
	public boolean isAdministrationWork() {
		return administrationWork;
	}

	@Override
	public void setAdministrationWork(boolean administrationWork) {
		this.administrationWork = administrationWork;
	}

	@Override
	public double getHoursA() {
		return hoursA;
	}

	@Override
	public void setHoursA(double hoursA) {
		this.hoursA = hoursA;
	}

}
