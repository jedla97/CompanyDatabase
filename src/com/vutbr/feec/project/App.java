package com.vutbr.feec.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
	private static final String MAVEN_RESOURCES_PREFIX = "./src/com/vutbr/feec/project/";

	public static void main(String[] args) {
		File persons = new File(MAVEN_RESOURCES_PREFIX + "persons.csv");
		int operation, profession, id;
		double help;
		String name = null, lastName = null;
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
				while (!sc.hasNextLine() && sc.nextLine() != "") {
					sc.next();
				}
				name = sc.nextLine();
				System.out.println("Input last name of employee");
				while (!sc.hasNextLine() && sc.nextLine() != "") {
					sc.next();
				}
				lastName = sc.nextLine();
				System.out.println("Input Id of employee");
				while (!sc.hasNextInt()) {
					sc.next();
				}
				id = sc.nextInt();
				profession = function.chooseProfesion();
				help = data.getMaxHourForEmployee();
				if (profession == 1) {
					Assistant assistant = new Assistant(id, name, lastName);
					if (data.checkIfIdExist(assistant.getId()) == 0) {
						assistant.setMaxHours(help);
						data.data.add(assistant);
						System.out.println("Employee with id " + id + " has been added");
					}
				} else if (profession == 2) {
					TechnicalWorker technicalWorker = new TechnicalWorker(id, name, lastName);
					if (data.checkIfIdExist(technicalWorker.getId()) == 0) {
						technicalWorker.setMaxHours(help);
						data.data.add(technicalWorker);
						System.out.println("Employee with id " + id + " has been added");
					}
				} else if (profession == 3) {
					Developer developer = new Developer(id, name, lastName);
					if (data.checkIfIdExist(developer.getId()) == 0) {
						developer.setMaxHours(help);
						data.data.add(developer);
						System.out.println("Employee with id " + id + " has been added");
					}
				} else if (profession == 4) {
					Director director = new Director(id, name, lastName);
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
				int help2;
				int help1 = function.chooseWhatToDo(4);
				help = function.howManyHours();
				help2 = data.setWork(help1, help);
				if(help2==-1) {
					System.out.println("Can't set the work. Don't enough work power.");
				}
				function.press2Continue();
			}
			// delete work
			else if (operation == 3) {
				int help1 = function.chooseWhatToDo(4);
				help = function.howManyHours();
				data.setWork(help1, help*(-1));
				function.press2Continue();
			}
			// activation of employee
			else if (operation == 4) {
				System.out.println("Input Id of employee");
				while (!sc.hasNextInt()) {
					sc.next();
				}
				id = sc.nextInt();
				if (data.isCapable(id) == true) {
					System.out.println("Employee is not capable of do this job");
				} else {
					int help1 = data.whatIsMyProfession(id);
					if (help1 == 1) {
						data.printEmployee(id);
					} else {
						int help2 = function.chooseWhatToDo(help1);
						if (help1 == 2 && help2 == 1) {
							data.printEmployee(id);
						} else if (help1 == 2 && help2 == 2) {
							data.printNumbersOfVowels(id);
						} else if (help1 == 3 && help2 == 1) {
							data.printNumbersOfVowels(id);
						} else if (help1 == 3 && help2 == 2) {
							data.printReverse(id);
						} else if (help1 == 4 && help2 == 1) {
							data.printEmployee(id);
						} else if (help1 == 4 && help2 == 2) {
							data.printNumbersOfVowels(id);
						} else if (help1 == 4 && help2 == 3) {
							data.printReverse(id);
						}
					}
				}
				function.press2Continue();
			}
			// fire employee
			else if (operation == 5) {
				function.press2Continue();
			}
			// start illness
			else if (operation == 6) {
				function.press2Continue();
			}
			// end illness
			else if (operation == 7) {
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
				System.out.println(help);
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
