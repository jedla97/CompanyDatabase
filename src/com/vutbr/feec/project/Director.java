package com.vutbr.feec.project;

import com.vutbr.feec.project.Employee;

public class Director extends Employee {

	static final int SALARY_PER_HOUR = 350;
	static final String profession = "Director";
	private boolean ill = false;
	public static int counter = 0;
	private double salary;
	private boolean administrationWork = false;
	private boolean technicalWork = false;
	private boolean devolopWork = false;
	private double hoursA = 0;
	private double hoursT = 0;
	private double hoursD = 0;

	public Director(Integer id, String name, String lastName) {
		super(id, name, lastName);
		counter++;
	}

	@Override
	public String showEmployeeData() {
		this.setSalary();
			return "name: " + this.getName() + " " + this.getLastName() + " working " + df.format(this.getHours())
					+ " hours, salary is " + df.format(this.getSalary()) + " and is not ill";
		
	}

	@Override
	public int vowels() {
		String name = this.getName() + " " + this.getLastName();
		name=name.toLowerCase();
		int vowels = 0;
		for (int i = 0; i < name.length(); i++) {
			char letter = name.charAt(i);
			if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == 'á'
					|| letter == 'é' || letter == 'í' || letter == 'ó' || letter == 'ú' || letter == 'ù'
					|| letter == 'ì' || letter == 'y' || letter == 'ý') {
				++vowels;
			}
		}
		return vowels;
	}

	@Override
	public String reverseName() {
		StringBuilder input = new StringBuilder();
		String name = this.getName() + " " + this.getLastName();
		input.append(name);
		name = input.reverse().toString();
		return name;
	}

	@Override
	public String toString() {
		String[] stringHelp = { this.getId().toString(), this.getName(), this.getLastName(), profession };
		return String.format(format, stringHelp) + " working on " + this.workJobs();
	}

	public String workJobs() {
		if (this.devolopWork == true && this.technicalWork == true && this.administrationWork == true) {
			return "administrative work with " + df.format(this.getHoursA()) + " hours, technical documentation with "
					+ df.format(this.getHoursT()) + " hours and development work with " + df.format(this.getHoursD())
					+ " hours";
		} else if (this.devolopWork == true && this.technicalWork == true) {
			return "technical documentation with " + df.format(this.getHoursT()) + " hours and development work with "
					+ df.format(this.getHoursD()) + " hours";
		} else if (this.technicalWork == true && this.administrationWork == true) {
			return "administrative work with " + df.format(this.getHoursA())
					+ " hours and technical documentation with " + df.format(this.getHoursT()) + " hours";
		} else if (this.devolopWork == true && this.administrationWork == true) {
			return "administrative work with " + df.format(this.getHoursA()) + " hours and development work with "
					+ df.format(this.getHoursD()) + " hours";
		} else if (this.administrationWork == true) {
			return "administrative work with " + df.format(this.getHoursA()) + " hours";
		} else if (this.devolopWork == true) {
			return "development work with " + df.format(this.getHoursD()) + " hours";
		} else if (this.technicalWork == true) {
			return "technical documentation" + df.format(this.getHoursT()) + " hours";
		} else {
			return "nothing";
		}
	}

	@Override
	public String toDatabase() {
		return profession + ";" + this.getId() + ";" + this.getName() + ";" + this.getLastName() + ";" + this.getHours()
				+ ";" + this.getHoursA() + ";" + this.getHoursT() + ";" + this.getHoursD() + ";"
				+ this.isAdministrationWork() + ";" + this.isTechnicalWork() + ";" + this.isDevolopWork() + ";"
				+ this.isIll() + "\n";
	}

	@Override
	public String getProfession() {
		return profession;
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
	public boolean isAdministrationWork() {
		return administrationWork;
	}

	@Override
	public void setAdministrationWork(boolean administrationWork) {
		this.administrationWork = administrationWork;
	}

	@Override
	public boolean isTechnicalWork() {
		return technicalWork;
	}

	@Override
	public void setTechnicalWork(boolean technicalWork) {
		this.technicalWork = technicalWork;
	}

	@Override
	public boolean isDevolopWork() {
		return devolopWork;
	}

	@Override
	public void setDevolopWork(boolean devolopWork) {
		this.devolopWork = devolopWork;
	}

	@Override
	public double getHoursA() {
		return hoursA;
	}

	@Override
	public void setHoursA(double hoursA) {
		this.hoursA = hoursA;
	}

	@Override
	public double getHoursT() {
		return hoursT;
	}

	@Override
	public void setHoursT(double hoursT) {
		this.hoursT = hoursT;
	}

	@Override
	public double getHoursD() {
		return hoursD;
	}

	@Override
	public void setHoursD(double hoursD) {
		this.hoursD = hoursD;
	}

}
