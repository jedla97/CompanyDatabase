package com.vutbr.feec.project;

import com.vutbr.feec.project.Employee;

public class Developer extends Employee {

	static final int SALARY_PER_HOUR = 250;
	static final String profession = "Developer";
	private boolean ill = false;
	private double salary;
	private double maxHours;
	private boolean technicalWork = false;
	private boolean devolopWork = false;
	private double hoursT = 0;
	private double hoursD = 0;

	public Developer(Integer id, String name, String lastName) {
		super(id, name, lastName);
	}

	// return string with type of work and how many hours do on that job
	public String workJobs() {
		if (this.devolopWork == true && this.technicalWork == true) {
			return "technical documentation with " + df.format(this.getHoursT()) + " hours and development work with "
					+ df.format(this.getHoursD()) + " hours";
		} else if (this.devolopWork == true) {
			return "development work with " + df.format(this.getHoursD()) + " hours";
		} else if (this.technicalWork == true) {
			return "technical documentation" + df.format(this.getHoursT()) + " hours";
		} else {
			return "nothing";
		}
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
	public int vowels() {
		String name = this.getName() + " " + this.getLastName();
		name = name.toLowerCase();
		int vowels = 0;
		for (int i = 0; i < name.length(); i++) {
			char letter = name.charAt(i);
			if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == '�'
					|| letter == '�' || letter == '�' || letter == '�' || letter == '�' || letter == '�'
					|| letter == '�' || letter == 'y' || letter == '�') {
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

	@Override
	public String toDatabase() {
		return profession + ";" + this.getId() + ";" + this.getName() + ";" + this.getLastName() + ";" + this.getHours()
				+ ";" + this.getMaxHours() + ";" + this.getHoursT() + ";" + this.getHoursD() + ";"
				+ this.isTechnicalWork() + ";" + this.isDevolopWork() + ";" + this.isIll() + "\n";
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
