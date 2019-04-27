package com.vutbr.feec.project;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Function {
	Scanner sc = new Scanner(System.in);
	public static DecimalFormat df = new DecimalFormat("#.##");

	public int operation() {
		int choose;
		String[] operation = { "add employee", "add work", "delete work", "activation of employee(do work)",
				"fire employee", "start employee illnes", "end illness", "obligation", "number of employees",
				"financial situation", "show all employee", "save database of employee", "load database of employee",
				"exit program" };
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

	public void realocationWrong(int errCode, double[] hours) {
		if (errCode == -1) {
			System.out.println(
					"Can't set hours for administration work " + df.format(hours[0]) + " is unasigned and disapear");
		} else if (errCode == -2) {
			System.out.println("Can't set hours for technical documentation " + df.format(hours[1])
					+ " is unasigned and disapear");
		} else if (errCode == -3) {
			System.out.println(
					"Can't set hours for developing work " + df.format(hours[2]) + " is unasigned and disapear");
		} else if (errCode == -4) {
			System.out.println(
					"Can't set hours for administration work " + df.format(hours[0]) + " is unasigned and disapear");
			System.out.println("Can't set hours for technical documentation " + df.format(hours[1])
					+ " is unasigned and disapear");
		} else if (errCode == -5) {
			System.out.println("Can't set hours for technical documentation " + df.format(hours[1])
					+ " is unasigned and disapear");
			System.out.println(
					"Can't set hours for developing work " + df.format(hours[2]) + " is unasigned and disapear");
		} else if (errCode == -6) {
			System.out.println(
					"Can't set hours for administration work " + df.format(hours[0]) + " is unasigned and disapear");
			System.out.println(
					"Can't set hours for developing work " + df.format(hours[2]) + " is unasigned and disapear");
		} else if (errCode == -7) {
			System.out.println(
					"Can't set hours for administration work " + df.format(hours[0]) + " is unasigned and disapear");
			System.out.println("Can't set hours for technical documentation " + df.format(hours[1])
					+ " is unasigned and disapear");
			System.out.println(
					"Can't set hours for developing work " + df.format(hours[2]) + " is unasigned and disapear");
		}
	}

	public void press2Continue() {
		System.out.print("press enter");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
