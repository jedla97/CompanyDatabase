package com.vutbr.feec.project;

import java.text.DecimalFormat;

// lot of methods is overwrite in subclasses
public class Employee {
	public static String format = "|%1$-8s|%2$-20s|%3$-20s|%4$-15s|";
	public static DecimalFormat df = new DecimalFormat("#.##");
	private Integer id;
	private String name;
	private String lastName;
	private double hours;

	public Employee(Integer id, String name, String lastName) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;

	}

// String for save to file
	public String toDatabase() {
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {

		return 0;
	}

	public void setSalary() {

	}

	public void setIll(boolean ill) {

	}

	public boolean isIll() {

		return false;
	}

// 744 = 31*24 is maximum hours in month // for director
	public double getMaxHours() {
		return 744;

	}

	public void setMaxHours(double maxHours) {
	}

	public String getProfession() {
		return null;
	}

	// print employee
	public String showEmployeeData() {
		return null;
	}

	// numbers of vowels
	public int vowels() {

		return -1;
	}

// reverse name
	public String reverseName() {
		return null;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public boolean isAdministrationWork() {
		return false;
	}

	public void setAdministrationWork(boolean administrationWork) {

	}

	public boolean isTechnicalWork() {
		return false;
	}

	public void setTechnicalWork(boolean technicalWork) {

	}

	public boolean isDevolopWork() {
		return false;
	}

	public void setDevolopWork(boolean devolopWork) {

	}

	public double getHoursA() {
		return 0;
	}

	public void setHoursA(double hoursA) {
	}

	public double getHoursT() {
		return 0;
	}

	public void setHoursT(double hoursT) {

	}

	public double getHoursD() {
		return 0;
	}

	public void setHoursD(double hoursD) {

	}

}
