/*
 * Create by Jakub Jedlicka
 * School project BPC2I
 * School BUT FEEC
 * school login: xjedli24 ; Id: 198597 ; email: xjedli24@stud.feec.vutbr.cz
 * personal email: jedlayt@gmail.com !for communication use this email and always subject with name of app
 */
//for execute app in cmd java -jar <file>.jar
package com.vutbr.feec.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class App {
	private static final String MAVEN_RESOURCES_PREFIX = "./src/com/vutbr/feec/project/";

// main part of app for choose which operation to do
	public static void main(String[] args) {
		final DecimalFormat df = new DecimalFormat("#.##");
		File persons = new File(MAVEN_RESOURCES_PREFIX + "persons.csv");
		int operation, profession, id, help1, help2;
		double help;
		double[] helpHours;
		String lastName = null, firstName = null;
		// boolean flag = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Function function = new Function();
		Database data = new Database();
		while (true) {
			operation = function.operation();
			// add employee
			if (operation == 1) {
				System.out.println("Input first name of employee");
				firstName = sc.next();
				System.out.println("Input last name of employee");
				lastName = sc.next();
				System.out.println("Input Id of employee");
				while (!sc.hasNextInt()) {
					sc.next();
				}
				id = sc.nextInt();
				profession = function.chooseProfesion();
				help = data.getMaxHourForEmployee();
				if (profession == 1) {
					Assistant assistant = new Assistant(id, firstName, lastName);
					if (data.checkIfIdExist(assistant.getId()) == 0) {
						assistant.setMaxHours(help);
						data.data.add(assistant);
						System.out.println("Employee with id " + id + " has been added");
					}
				} else if (profession == 2) {
					TechnicalWorker technicalWorker = new TechnicalWorker(id, firstName, lastName);
					if (data.checkIfIdExist(technicalWorker.getId()) == 0) {
						technicalWorker.setMaxHours(help);
						data.data.add(technicalWorker);
						System.out.println("Employee with id " + id + " has been added");
					}
				} else if (profession == 3) {
					Developer developer = new Developer(id, firstName, lastName);
					if (data.checkIfIdExist(developer.getId()) == 0) {
						developer.setMaxHours(help);
						data.data.add(developer);
						System.out.println("Employee with id " + id + " has been added");
					}
				} else if (profession == 4) {
					Director director = new Director(id, firstName, lastName);
					if (data.checkIfIdExist(director.getId()) == 0) {
						if (Director.counter <= 1) {
							data.data.add(director);
							System.out.println("Employee with id " + id + " has been added");
						} else {
							System.out.println("Director alredy exist and you can't have more than one");
						}
					}

				}
				function.press2Continue();
			}
			// add work
			else if (operation == 2) {
				help1 = function.chooseWhatToDo(4);
				help = function.howManyHours();
				help2 = data.setWork(help1, help);
				if (help2 == -1) {
					System.out.println("Can't set the work. Don't enough work power.");
				}
				function.press2Continue();
			}
			// delete work
			else if (operation == 3) {
				help1 = function.chooseWhatToDo(4);
				help = function.howManyHours();
				data.setWork(help1, help * (-1));
				function.press2Continue();
			}
			// activation of employee
			else if (operation == 4) {
				System.out.println("Input Id of employee");
				while (!sc.hasNextInt()) {
					sc.next();
				}
				id = sc.nextInt();
				if (data.isCapable(id, 4) == true) {
					System.out.println("Employee is not capable of do this job");
				} else {
					help1 = data.whatIsMyProfession(id);
					if (help1 == 1) {
						if (data.isCapable(id, 1) == true) {
							System.out.println("Employee is not capable of do this job");
						} else {
							data.printEmployee(id);
						}
					} else {
						help2 = function.chooseWhatToDo(help1);
						if (help1 == 2 && help2 == 1) {
							if (data.isCapable(id, 1) == true) {
								System.out.println("Employee is not capable of do this job");
							} else {
								data.printEmployee(id);
							}
						} else if (help1 == 2 && help2 == 2) {
							if (data.isCapable(id, 2) == true) {
								System.out.println("Employee is not capable of do this job");
							} else {
								data.printNumbersOfVowels(id);
							}
						} else if (help1 == 3 && help2 == 1) {
							if (data.isCapable(id, 2) == true) {
								System.out.println("Employee is not capable of do this job");
							} else {
								data.printNumbersOfVowels(id);
							}
						} else if (help1 == 3 && help2 == 2) {
							if (data.isCapable(id, 3) == true) {
								System.out.println("Employee is not capable of do this job");
							} else {
								data.printReverse(id);
							}
						} else if (help1 == 4 && help2 == 1) {
							if (data.isCapable(id, 1) == true) {
								System.out.println("Employee is not capable of do this job");
							} else {
								data.printEmployee(id);
							}
						} else if (help1 == 4 && help2 == 2) {
							if (data.isCapable(id, 2) == true) {
								System.out.println("Employee is not capable of do this job");
							} else {
								data.printNumbersOfVowels(id);
							}
						} else if (help1 == 4 && help2 == 3) {
							if (data.isCapable(id, 3) == true) {
								System.out.println("Employee is not capable of do this job");
							} else {
								data.printReverse(id);
							}
						}
					}
				}
				function.press2Continue();
			}
			// fire employee
			else if (operation == 5) {
				System.out.println("Input Id of employee");
				while (!sc.hasNextInt()) {
					sc.next();
				}
				id = sc.nextInt();
				profession = data.whatIsMyProfession(id);
				helpHours = data.employeeHours(id);
				if (helpHours[0] == -1) {

				} else {
					data.fireEmployee(id);
					help1 = data.reallocation(helpHours[0], helpHours[1], helpHours[2], profession);
					function.realocationWrong(help1, helpHours);
				}
				function.press2Continue();

			}
			// start illness
			else if (operation == 6) {
				System.out.println("Input Id of employee");
				while (!sc.hasNextInt()) {
					sc.next();
				}
				id = sc.nextInt();
				profession = data.whatIsMyProfession(id);
				helpHours = data.employeeHours(id);

				if (helpHours[0] == -1) {

				} else {
					data.startIlness(id);
					help1 = data.reallocation(helpHours[0], helpHours[1], helpHours[2], profession);
					function.realocationWrong(help1, helpHours);
				}
				function.press2Continue();
			}
			// end illness
			else if (operation == 7) {
				System.out.println("Input Id of employee");
				while (!sc.hasNextInt()) {
					sc.next();
				}
				id = sc.nextInt();
				data.endIlness(id);
				function.press2Continue();
			}
			// obligation
			else if (operation == 8) {
				help = function.howManyHours();
				data.obligation(help);
				function.press2Continue();
			}
			// number of employee
			else if (operation == 9) {
				data.numberOfEmployeeAndFreeObligation();
				function.press2Continue();
			}
			// finance
			else if (operation == 10) {
				help = data.companySalary();
				System.out.println("Company costs are " + df.format(help) + "$");
				function.press2Continue();
			}
			// show all employee
			else if (operation == 11) {
				data.printAllEmployee();
				function.press2Continue();
			}
			// database save
			else if (operation == 12) {
				try {
					data.save(persons);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				function.press2Continue();
			}
			// load database
			else if (operation == 13) {
				try {
					data.load(persons, data);

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				function.press2Continue();
			}
			// end program
			else if (operation == 14) {
				System.out.println("program has been ended due to player end");
				break;
				// System.exit(99);
			}
		}

	}

}
