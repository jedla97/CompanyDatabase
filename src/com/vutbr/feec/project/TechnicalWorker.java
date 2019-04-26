package com.vutbr.feec.project;

import java.text.DecimalFormat;

import com.vutbr.feec.project.Employee;

public class TechnicalWorker extends Employee {
	static final int SALARY_PER_HOUR = 200;
	static final String profession = "TechnicalWorker";
	private boolean ill = false;
	private double salary;
	private double maxHours;
	private boolean administrationWork = false;
	private boolean technicalWork = false;
	private double hoursA = 0;
	private double hoursT = 0;

	public TechnicalWorker(Integer id, String name, String lastName) {
		super(id, name, name);
	}

	@Override
	public String showEmployeeData() {
		this.setSalary();
		if (this.isIll() == false) {
			return "name: " + this.getName() + " " + this.getLastName() + " working " + this.getHours()
					+ " hours, salary is " + this.getSalary() + " and is not ill";
		} else {
			return "name: " + this.getName() + " " + this.getLastName() + " working " + this.getHours()
					+ " hours, salary is " + this.getSalary() + " and is ill";
		}
	}

	// for numbers of vowels in czech alphabet
	@Override
	public int vowels() {
		String name = this.getName() + " " + this.getLastName();
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
	public String toString() {
		String[] stringHelp = { this.getId().toString(), this.getName(), this.getLastName(), profession };
		return String.format(format, stringHelp) + " working on " + this.workJobs();
	}

	public String workJobs() {
		if (this.administrationWork == true && this.technicalWork == true) {
			return "administrative work with " + df.format(this.getHoursA())
					+ " hours and technical documentation with " + df.format(this.getHoursT()) + " hours";
		} else if (this.administrationWork == true) {
			return "administrative work with " + df.format(this.getHoursA()) + " hours";
		} else if (this.technicalWork == true) {
			return "technical documentation" + df.format(this.getHoursT()) + " hours";
		} else {
			return "nothing";
		}
	}

	@Override
	public String toDatabase() {
		return profession + ";" + this.getId() + ";" + this.getName() + ";" + this.getLastName() + ";" + this.getHours()
				+ ";" + this.getMaxHours() + ";" + this.getHoursA() + ";" + this.getHoursT() + ";"
				+ this.isAdministrationWork() + ";" + this.isTechnicalWork() + ";" + this.isIll() + "\n";
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
	public boolean isTechnicalWork() {
		return technicalWork;
	}

	@Override
	public void setTechnicalWork(boolean technicalWork) {
		this.technicalWork = technicalWork;
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
}
