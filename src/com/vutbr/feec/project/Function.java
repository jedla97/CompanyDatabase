package com.vutbr.feec.project;

import java.io.IOException;
import java.util.Scanner;

public class Function {
	Scanner sc = new Scanner(System.in);

	public int operation() {
		int choose;
		String[] operation = { "add employee", "add work", "delete work", "aktivace zamestnance", "fire employee",
				"start employee illnes", "end illness", "obligation", "pocet zamestnancu", "finance",
				"show all employee", "save database of employee", "load database of employee", "end" };
		for (int i = 0; i < operation.length; i++) {
			System.out.println("For " + operation[i] + " [ input " + (i + 1) + " ]");
		}
		while (true) {
			while (!sc.hasNextInt()) {
				sc.next();
			}
			choose = sc.nextInt();
			if (choose <= 14 && choose >= 1) {
				return choose;
			}
		}
	}

	public int chooseProfesion() {
		int choose;
		String[] profession = { "Asistant", "TechnicalWorker", "Developer", "Director" };
		for (int i = 0; i < profession.length; i++) {
			System.out.println("For " + profession[i] + " [ input " + (i + 1) + " ]");
		}
		while (true) {
			while (!sc.hasNextInt()) {
				sc.next();
			}
			choose = sc.nextInt();
			if (choose <= 4 && choose >= 1) {
				return choose;
			}
		}

	}

	public double howManyHours() {
		double choose;
		System.out.println("how many hours: ");
		while (true) {
			while (!sc.hasNextDouble()) {
				sc.next();
			}
			choose = sc.nextDouble();
			if (choose >= 0) {
				return choose;
			}
		}
	}

	public int chooseWhatToDo(int prof) {
		int choose;
		String[] work = { "Info about employee", "Technical work", "Develop work" };
		if (prof == 2) {
			System.out.println("For " + work[0] + " [ input 1 ]");
			System.out.println("For " + work[1] + " [ input 2 ]");
			while (true) {
				while (!sc.hasNextInt()) {
					sc.next();
				}
				choose = sc.nextInt();
				if (choose <= 2 && choose >= 1) {
					return choose;
				}
			}
		} else if (prof == 3) {
			System.out.println("For " + work[1] + " [ input 1 ]");
			System.out.println("For " + work[2] + " [ input 2 ]");
			while (true) {
				while (!sc.hasNextInt()) {
					sc.next();
				}
				choose = sc.nextInt();
				if (choose <= 2 && choose >= 1) {
					return choose;
				}
			}
		} else if (prof == 4) {
			System.out.println("For " + work[0] + " [ input 1 ]");
			System.out.println("For " + work[1] + " [ input 2 ]");
			System.out.println("For " + work[2] + " [ input 3 ]");
			while (true) {
				while (!sc.hasNextInt()) {
					sc.next();
				}
				choose = sc.nextInt();
				if (choose <= 3 && choose >= 1) {
					return choose;
				}
			}
		}
		return 0;

	}
/*
	public String scan(int idOperation) {
		String name;
		if (idOperation == 1) {
			System.out.println("Input first name of employee");
		} else if (idOperation == 2) {
			System.out.println("Input last name of employee");
		}
		while (!sc.hasNextLine()&& sc.nextLine() != "") {
			sc.next();
		}
		name = sc.nextLine();
		return name;
	}
*/
	public void press2Continue() {
		System.out.print("press enter");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
