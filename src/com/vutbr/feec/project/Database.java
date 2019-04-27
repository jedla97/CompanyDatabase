package com.vutbr.feec.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Database implements Comparator<Employee> {
	List<Employee> data = new ArrayList<Employee>();
	final String COMMA_DELIMITER = ",";
	final String LINE_SEPARATOR = "\n";
	public static String format = "|%1$-8s|%2$-20s|%3$-20s|%4$-15s|\n";
	public static DecimalFormat df = new DecimalFormat("#.##");
	
	Comparator<Employee> compareByLastName = (Employee o1, Employee o2) -> o1.getLastName().compareTo(o2.getLastName());
	Comparator<Employee> compareById = (Employee o1, Employee o2) -> o1.getId().compareTo(o2.getId());

	public void printAllEmployee() {
		int choose;
		String[] operation = { " by Id ", " by last name " };
		String[] header = { "ID", "first name", "last name", "profession" };
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < operation.length; i++) {
			System.out.println("For " + operation[i] + " [ input " + (i + 1) + " ]");
		}
		while (true) {
			while (!sc.hasNextInt()) {
				sc.next();
			}
			choose = sc.nextInt();
			if (choose == 1) {
				Collections.sort(data, compareById);
				System.out.format(format, header);
				for (Employee employee : data) {
					System.out.println(employee);
				}
				break;
			} else if (choose == 2) {
				Collections.sort(data, compareByLastName);
				System.out.format(format, header);
				for (Employee employee : data) {
					System.out.println(employee);
				}
				break;
			}
		}
	}

	public void printEmployee(int id) {
		int help;
		help = this.employeeId(id);
		if (help == -1) {
			System.out.println("employee with Id " + id + " doesn't exit");
		} else {
			System.out.println(data.get(help).showEmployeeData());
		}
	}

	public void printNumbersOfVowels(int id) {
		int help, vowels;
		help = this.employeeId(id);
		if (help == -1) {
			System.out.println("employee with Id " + id + " doesn't exit");
		} else {
			vowels = data.get(help).vowels();
			System.out.println("Number of vowels is " + vowels + " in name" + data.get(help).getName() + " "
					+ data.get(help).getLastName());
		}
	}

	public void printReverse(int id) {
		int help;
		String name;
		help = this.employeeId(id);
		if (help == -1) {
			System.out.println("employee with Id " + id + " doesn't exit");
		} else {
			name = data.get(help).reverseName();
			System.out.println(
					"reverse of " + data.get(help).getName() + " " + data.get(help).getLastName() + " name is " + name);
		}
	}

	public int checkIfIdExist(int id) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getId() == id) {
				System.out.println("Employee with id " + id + " exist");
				return 1;
			}
		}
		return 0;
	}

	public int employeeId(int arrayId) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getId() == arrayId) {
				return i;
			}
		}
		return -1;
	}

	public void save(File persons) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(persons), true);
		for (int i = 0; i < data.size(); i++) {
			String tmp = data.get(i).toDatabase();
			pw.write(tmp);
		}
		pw.close();
		System.out.println("Employee database was save successfully");
	}

	public void load(File persons, Database data) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(persons));
		String line = "";
		String cvsSplitBy = ";";
		try {
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] employee = line.split(cvsSplitBy);
				if (employee[0].equals("Assistant")) {
					Assistant assistant = new Assistant(Integer.valueOf(employee[1]), employee[2], employee[3]);
					if (data.checkIfIdExist(assistant.getId()) == 0) {
						assistant.setHours(Double.valueOf(employee[4]));
						assistant.setMaxHours(Double.valueOf(employee[5]));
						assistant.setHoursA(Double.valueOf(employee[6]));
						assistant.setAdministrationWork(Boolean.valueOf(employee[7]));
						assistant.setIll(Boolean.valueOf(employee[8]));
						data.data.add(assistant);
					}
				} else if (employee[0].equals("TechnicalWorker")) {
					TechnicalWorker technicalWorker = new TechnicalWorker(Integer.valueOf(employee[1]), employee[2],
							employee[3]);
					if (data.checkIfIdExist(technicalWorker.getId()) == 0) {
						technicalWorker.setHours(Double.valueOf(employee[4]));
						technicalWorker.setMaxHours(Double.valueOf(employee[5]));
						technicalWorker.setHoursA(Double.valueOf(employee[6]));
						technicalWorker.setHoursT(Double.valueOf(employee[7]));
						technicalWorker.setAdministrationWork(Boolean.valueOf(employee[8]));
						technicalWorker.setTechnicalWork(Boolean.valueOf(employee[9]));
						technicalWorker.setIll(Boolean.valueOf(employee[10]));
						data.data.add(technicalWorker);
					}
				} else if (employee[0].equals("Developer")) {
					Developer developer = new Developer(Integer.valueOf(employee[1]), employee[2], employee[3]);
					if (data.checkIfIdExist(developer.getId()) == 0) {
						developer.setHours(Double.valueOf(employee[4]));
						developer.setMaxHours(Double.valueOf(employee[5]));
						developer.setHoursT(Double.valueOf(employee[6]));
						developer.setHoursD(Double.valueOf(employee[7]));
						developer.setTechnicalWork(Boolean.valueOf(employee[8]));
						developer.setDevolopWork(Boolean.valueOf(employee[9]));
						developer.setIll(Boolean.valueOf(employee[10]));
						data.data.add(developer);
					}
				} else if (employee[0].equals("Director")) {
					Director director = new Director(Integer.valueOf(employee[1]), employee[2], employee[3]);
					if (data.checkIfIdExist(director.getId()) == 0) {
						if (Director.counter <= 1) {
							director.setHours(Double.valueOf(employee[4]));
							director.setHoursA(Double.valueOf(employee[5]));
							director.setHoursT(Double.valueOf(employee[6]));
							director.setHoursD(Double.valueOf(employee[7]));
							director.setAdministrationWork(Boolean.valueOf(employee[8]));
							director.setTechnicalWork(Boolean.valueOf(employee[9]));
							director.setDevolopWork(Boolean.valueOf(employee[10]));
							director.setIll(Boolean.valueOf(employee[11]));
							data.data.add(director);
						} else {
							System.out.println("Director alredy exist and you can't have more than one");
						}
					}

				}
			}
			System.out.println("Employee database was load successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double companySalary() {
		double salaryHelp = 0;
		for (int i = 0; i < data.size(); i++) {
			data.get(i).setSalary();
			salaryHelp = salaryHelp + data.get(i).getSalary();
		}
		return salaryHelp;

	}

	public void obligation(double hours) {
		for (int i = 0; i < data.size(); i++) {
			data.get(i).setMaxHours(hours);
		}
	}

	public void numberOfEmployeeAndFreeObligation() {
		int counterA = 0, counterT = 0, counterDev = 0, counterDir = 0;
		double obligationA = 0, obligationT = 0, obligationDev = 0, obligationDir = 0;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getProfession().equals("Assistant")) {
				counterA++;
				if (data.get(i).isIll() == false) {
					obligationA = obligationA + (data.get(i).getMaxHours() - data.get(i).getHours());
				}
			} else if (data.get(i).getProfession().equals("TechnicalWorker")) {
				counterT++;
				if (data.get(i).isIll() == false) {
					obligationT = obligationT + (data.get(i).getMaxHours() - data.get(i).getHours());
				}
			} else if (data.get(i).getProfession().equals("Developer")) {
				counterDev++;
				if (data.get(i).isIll() == false) {
					obligationDev = obligationDev + (data.get(i).getMaxHours() - data.get(i).getHours());
				}
			} else if (data.get(i).getProfession().equals("Director")) {
				counterDir++;
				if (data.get(i).isIll() == false) {
					obligationDir = obligationDir + (data.get(i).getMaxHours() - data.get(i).getHours());
				}
			}
		}
		System.out.println("Number of Assistants: " + counterA + " and their free obligation: " + df.format(obligationA)
				+ "\nNumber of TechnicalWorkers: " + counterT + " and their free obligation: " + df.format(obligationT)
				+ "\nNumber of Developers: " + counterDev + " and their free obligation: " + df.format(obligationDev)
				+ "\nNumber of Directors: " + counterDir + " and their free obligation: " + df.format(obligationDir));

	}

	public int whatIsMyProfession(int id) {
		int help = this.employeeId(id);
		String prof;
		if (help == -1) {
			System.out.println("employee with Id " + id + " doesn't exit");
		} else {
			prof = data.get(help).getProfession();
			if (prof.equals("Assistant")) {
				return 1;
			} else if (prof.equals("TechnicalWorker")) {
				return 2;
			} else if (prof.equals("Developer")) {
				return 3;
			} else if (prof.equals("Director")) {
				return 4;
			}
		}
		return 0;
	}

	public boolean isCapable(int id) {
		int help = this.employeeId(id);
		if (help == -1) {
		} else if (data.get(help).isIll() == true || data.get(help).getHours() == 0) {
			return true;
		}
		return false;
	}

	public int workAssistant(double hours) {
		int counterA = 0, counterT = 0, counterDir = 0;
		/*
		 * helpHours, helpHoursDivide, helpHoursDivide2 is for divide work, maxHour is
		 * default 744 and is max hours set by obligation[8]
		 * 
		 * hourA is sum of assistant hours
		 * 
		 * technic: hourAt is sum of assistant hours ;; maxHourT is maximum work what is
		 * technic doing on technicalwork
		 * 
		 * 
		 * director: hourAdi is sum of assistant hours ;; maxHourTDi is maximum work
		 * what is director doing on technicalwork ;; maxHourDDi is maximum work what is
		 * director doing on developerwork
		 */
		double helpHours = 0, helpHoursDivide = 0, helpHoursDivide2 = 0, maxHourT = 0, maxHourTDi = 0, maxHourDDi = 0,
				hourA = 0, hourAt = 0, hourAdi = 0, maxHour = 744;

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getProfession().equals("Assistant")) {
				if (data.get(i).isIll() == false) {
					counterA++;
					maxHour = data.get(i).getMaxHours();
					hourA = hourA + data.get(i).getHoursA();

				}
			} else if (data.get(i).getProfession().equals("TechnicalWorker")) {
				if (data.get(i).isIll() == false) {
					counterT++;
					maxHour = data.get(i).getMaxHours();
					hourAt = hourAt + data.get(i).getHoursA();
					helpHours = data.get(i).getHoursT();
					if (helpHours > maxHourT) {
						maxHourT = helpHours;
					}

				}
			} else if (data.get(i).getProfession().equals("Director")) {
				if (data.get(i).isIll() == false) {
					counterDir++;
					hourAdi = hourAdi + data.get(i).getHoursA();
					helpHours = data.get(i).getHoursT();
					if (data.get(i).getHoursA() > maxHourTDi) {
						maxHourTDi = helpHours;
					}
					helpHours = data.get(i).getHoursD();
					if (data.get(i).getHoursA() > maxHourDDi) {
						maxHourDDi = helpHours;
					}

				}
			}
		}
		if ((hours * (-1)) > (hourA + hourAt + hourAdi)) {
			System.out.println(
					"it's not enought hours to substract max hours to substract is " + (hourA + hourAt + hourAdi));
			return -2;
		}
		helpHours = (hourA + hourAt + hourAdi + hours);
		if (counterA > 0) {
			if ((helpHours / counterA) <= maxHour) {
				for (int i = 0; i < data.size(); i++) {
					if (data.get(i).getProfession().equals("Assistant")) {
						if (data.get(i).isIll() == false) {
							data.get(i).setHoursA(helpHours / counterA);
							data.get(i).setAdministrationWork(true);
						}
					} else if ((data.get(i).getProfession().equals("TechnicalWorker")
							|| data.get(i).getProfession().equals("Director"))) {
						data.get(i).setHoursA(0);
						data.get(i).setAdministrationWork(false);
					}
				}
				this.setHoursAfterWork();
				return 0;
			} else if (counterT > 0) {
				helpHoursDivide = maxHour * counterA; // assistant hours
				helpHours = helpHours - helpHoursDivide; // rest of hours for next technical
				if ((helpHours / counterT) <= (maxHour - maxHourT)) {
					for (int i = 0; i < data.size(); i++) {
						if (data.get(i).getProfession().equals("Assistant")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursA(helpHoursDivide / counterA);
								data.get(i).setAdministrationWork(true);
							}
						} else if (data.get(i).getProfession().equals("TechnicalWorker")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursA(helpHours / counterT);
								data.get(i).setAdministrationWork(true);
							}

						} else if (data.get(i).getProfession().equals("Director")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursA(0);
								data.get(i).setAdministrationWork(false);
							}
						}
					}
					this.setHoursAfterWork();
					return 0;
				} else if (counterDir > 0) {
					helpHoursDivide2 = (maxHour - maxHourT) * counterT; // work for technical assistant
					helpHours = helpHours - helpHoursDivide2; // rest of hours for next Director
					if ((helpHours / counterDir) <= (744 - maxHourTDi - maxHourDDi)) {
						for (int i = 0; i < data.size(); i++) {
							if (data.get(i).getProfession().equals("Assistant")) {
								if (data.get(i).isIll() == false) {
									data.get(i).setHoursA(helpHoursDivide / counterA);
									data.get(i).setAdministrationWork(true);
								}
							} else if (data.get(i).getProfession().equals("TechnicalWorker")) {
								if (data.get(i).isIll() == false) {
									data.get(i).setHoursA(helpHoursDivide2 / counterT);
									data.get(i).setAdministrationWork(true);
								}
							} else if (data.get(i).getProfession().equals("Director")) {
								if (data.get(i).isIll() == false) {
									data.get(i).setHoursA(helpHours / counterDir);
									data.get(i).setAdministrationWork(true);
								}

							}
						}
						this.setHoursAfterWork();
						return 0;
					} else {
						return -1;
					}
				} else {
					return -1;
				}
			} else if (counterDir > 0) {
				helpHoursDivide = maxHour * counterA; // work for assistant
				helpHours = helpHours - helpHoursDivide; // rest of hours for next Director
				if ((helpHours / counterDir) <= (744 - maxHourTDi - maxHourDDi)) {
					for (int i = 0; i < data.size(); i++) {
						if (data.get(i).getProfession().equals("Assistant")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursA(helpHoursDivide / counterA);
								data.get(i).setAdministrationWork(true);
							}
						} else if (data.get(i).getProfession().equals("Director")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursA(helpHours / counterDir);
								data.get(i).setAdministrationWork(true);
							}

						}
					}
					this.setHoursAfterWork();
					return 0;
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		} else if (counterT > 0) {
			if ((helpHours / counterT) <= (maxHour - maxHourT)) {
				for (int i = 0; i < data.size(); i++) {
					if (data.get(i).getProfession().equals("TechnicalWorker")) {
						if (data.get(i).isIll() == false) {
							data.get(i).setHoursA(helpHours / counterT);
							data.get(i).setAdministrationWork(true);
						}
					} else if (data.get(i).getProfession().equals("Director")) {
						data.get(i).setHoursA(0);
						data.get(i).setAdministrationWork(false);
					}
				}
				this.setHoursAfterWork();
				return 0;
			} else if (counterDir > 0) {
				helpHoursDivide = (maxHour - maxHourT) * counterT;
				helpHours = helpHours - helpHoursDivide;
				if ((helpHours / counterDir) <= (744 - maxHourTDi - maxHourDDi)) {
					for (int i = 0; i < data.size(); i++) {
						if (data.get(i).getProfession().equals("TechnicalWorker")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursA(helpHoursDivide / counterT);
								data.get(i).setAdministrationWork(true);
							}
						} else if (data.get(i).getProfession().equals("Director")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursA(helpHours / counterDir);
								data.get(i).setAdministrationWork(true);
							}

						}
					}
					this.setHoursAfterWork();
					return 0;
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		} else if (counterDir > 0) {
			if ((helpHours / counterDir) <= (744 - maxHourTDi - maxHourDDi)) {
				for (int i = 0; i < data.size(); i++) {
					if (data.get(i).getProfession().equals("Director")) {
						if (data.get(i).isIll() == false) {
							data.get(i).setHoursA(helpHours / counterDir);
							data.get(i).setAdministrationWork(true);
						}

					}
				}
				this.setHoursAfterWork();
				return 0;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	public int workTechnical(double hours) {
		int counterT = 0, counterDev = 0, counterDir = 0;
		/*
		 * helpHours, helpHoursDivide, helpHoursDivide2 is for divide work, maxHour is
		 * default 744 and is max hours set by obligation[8]
		 * 
		 * hourT is sum of technic technical hours maxHourA is maxim of 1 technical
		 * worker assist work
		 * 
		 * developer hourTdev sum of techniocal work maxHourD maximal developer workl
		 * 
		 * director hourTdi sum of technical work maxHourAdi maximal assistants work
		 * maxHourDdi maximal developer work
		 */
		double helpHours = 0, helpHoursDivide = 0, helpHoursDivide2 = 0, maxHourA = 0, maxHourD = 0, maxHourADi = 0,
				maxHourDDi = 0, hourT = 0, hourTdev = 0, hourTdi = 0, maxHour = 744;

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getProfession().equals("TechnicalWorker")) {
				if (data.get(i).isIll() == false) {
					counterT++;
					maxHour = data.get(i).getMaxHours();
					hourT = hourT + data.get(i).getHoursT();
					helpHours = data.get(i).getHoursA();
					if (helpHours > maxHourA) {
						maxHourA = helpHours;
					}

				}
			} else if (data.get(i).getProfession().equals("Developer")) {
				if (data.get(i).isIll() == false) {
					counterDev++;
					maxHour = data.get(i).getMaxHours();
					hourTdev = hourTdev + data.get(i).getHoursT();
					helpHours = data.get(i).getHoursD();
					if (helpHours > maxHourD) {
						maxHourD = helpHours;
					}

				}
			} else if (data.get(i).getProfession().equals("Director")) {
				if (data.get(i).isIll() == false) {
					counterDir++;
					hourTdi = hourTdi + data.get(i).getHoursT();
					helpHours = data.get(i).getHoursA();
					if (helpHours > maxHourADi) {
						maxHourADi = helpHours;
					}
					helpHours = data.get(i).getHoursD();
					if (helpHours > maxHourDDi) {
						maxHourDDi = helpHours;
					}

				}
			}
		}

		if ((hours * (-1)) > (hourT + hourTdev + hourTdi)) {
			System.out.println(
					"it's not enought hours to substract max hours to substract is " + (hourT + hourTdev + hourTdi));
			return -2;
		}
		helpHours = (hourT + hourTdev + hourTdi + hours);
		if (counterT > 0) {
			if ((helpHours / counterT) <= (maxHour - maxHourA)) {
				for (int i = 0; i < data.size(); i++) {
					if (data.get(i).getProfession().equals("TechnicalWorker")) {
						if (data.get(i).isIll() == false) {
							data.get(i).setHoursT(helpHours / counterT);
							data.get(i).setTechnicalWork(true);
						}
					} else if ((data.get(i).getProfession().equals("Developer")
							|| data.get(i).getProfession().equals("Director"))) {
						data.get(i).setHoursT(0);
						data.get(i).setTechnicalWork(false);
					}
				}
				this.setHoursAfterWork();
				return 0;
			} else if (counterDev > 0) {
				helpHoursDivide = (maxHour - maxHourA) * counterDev; // assistant hours
				helpHours = helpHours - helpHoursDivide; // rest of hours for next technical
				if ((helpHours / counterDev) <= (maxHour - maxHourD)) {
					for (int i = 0; i < data.size(); i++) {
						if (data.get(i).getProfession().equals("TechnicalWorker")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursT(helpHoursDivide / counterT);
								data.get(i).setTechnicalWork(true);
							}
						} else if (data.get(i).getProfession().equals("Developer")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursT(helpHours / counterDev);
								data.get(i).setTechnicalWork(true);
							}

						} else if (data.get(i).getProfession().equals("Director")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursT(0);
								data.get(i).setTechnicalWork(false);
							}
						}
					}
					this.setHoursAfterWork();
					return 0;
				} else if (counterDir > 0) {
					helpHoursDivide2 = (maxHour - maxHourD) * counterT; // work for technical assistant
					helpHours = helpHours - helpHoursDivide2; // rest of hours for next Director
					if ((helpHours / counterDir) <= (744 - maxHourADi - maxHourDDi)) {
						for (int i = 0; i < data.size(); i++) {
							if (data.get(i).getProfession().equals("TechnicalWorker")) {
								if (data.get(i).isIll() == false) {
									data.get(i).setHoursT(helpHoursDivide / counterT);
									data.get(i).setTechnicalWork(true);
								}
							} else if (data.get(i).getProfession().equals("Developer")) {
								if (data.get(i).isIll() == false) {
									data.get(i).setHoursT(helpHoursDivide2 / counterDev);
									data.get(i).setTechnicalWork(true);
								}
							} else if (data.get(i).getProfession().equals("Director")) {
								if (data.get(i).isIll() == false) {
									data.get(i).setHoursT(helpHours / counterDir);
									data.get(i).setTechnicalWork(true);
								}

							}
						}
						this.setHoursAfterWork();
						return 0;
					} else {
						return -1;
					}
				} else {
					return -1;
				}
			} else if (counterDir > 0) {
				helpHoursDivide = (maxHour - maxHourA) * counterT; // work for assistant
				helpHours = helpHours - helpHoursDivide; // rest of hours for next Director
				if ((helpHours / counterDir) <= (744 - maxHourADi - maxHourDDi)) {
					for (int i = 0; i < data.size(); i++) {
						if (data.get(i).getProfession().equals("TechnicalWorker")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursT(helpHoursDivide / counterT);
								data.get(i).setTechnicalWork(true);
							}
						} else if (data.get(i).getProfession().equals("Director")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursT(helpHours / counterDir);
								data.get(i).setTechnicalWork(true);
							}

						}
					}
					this.setHoursAfterWork();
					return 0;
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		} else if (counterDev > 0) {
			if ((helpHours / counterDev) <= (maxHour - maxHourD)) {
				for (int i = 0; i < data.size(); i++) {
					if (data.get(i).getProfession().equals("Developer")) {
						if (data.get(i).isIll() == false) {
							data.get(i).setHoursT(helpHours / counterDev);
							data.get(i).setTechnicalWork(true);
						}
					} else if (data.get(i).getProfession().equals("Director")) {
						data.get(i).setHoursT(0);
						data.get(i).setTechnicalWork(false);
					}
				}
				this.setHoursAfterWork();
				return 0;
			} else if (counterDir > 0) {
				helpHoursDivide = (maxHour - maxHourD) * counterDev;
				helpHours = helpHours - helpHoursDivide;
				if ((helpHours / counterDir) <= (744 - maxHourADi - maxHourDDi)) {
					for (int i = 0; i < data.size(); i++) {
						if (data.get(i).getProfession().equals("Developer")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursT(helpHoursDivide / counterDev);
								data.get(i).setTechnicalWork(true);
							}
						} else if (data.get(i).getProfession().equals("Director")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursT(helpHours / counterDir);
								data.get(i).setTechnicalWork(true);
							}

						}
					}
					this.setHoursAfterWork();
					return 0;
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		} else if (counterDir > 0) {
			if ((helpHours / counterDir) <= (744 - maxHourADi - maxHourDDi)) {
				for (int i = 0; i < data.size(); i++) {
					if (data.get(i).getProfession().equals("Director")) {
						if (data.get(i).isIll() == false) {
							data.get(i).setHoursT(helpHours / counterDir);
							data.get(i).setTechnicalWork(true);
						}

					}
				}
				this.setHoursAfterWork();
				return 0;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	public int workDeveloper(double hours) {
		int counterDev = 0, counterDir = 0;
		/*
		 * helpHours, helpHoursDivide is for divide work, maxHour is default 744 and is
		 * max hours set by obligation[8]
		 * 
		 * developer hourD sum of developer work maxHourD maximal technical work
		 * 
		 * director hourDdi sum of technical work maxHourAdi maximal assistants work
		 * maxHourTdi maximal developer work
		 */
		double helpHours = 0, helpHoursDivide = 0, maxHourD = 0, maxHourADi = 0, maxHourTDi = 0, hourD = 0, hourDdi = 0,
				maxHour = 744;

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getProfession().equals("Developer")) {
				if (data.get(i).isIll() == false) {
					counterDev++;
					maxHour = data.get(i).getMaxHours();
					hourD = hourD + data.get(i).getHoursD();
					helpHours = data.get(i).getHoursT();
					if (helpHours > maxHourD) {
						maxHourD = helpHours;
					}

				}
			} else if (data.get(i).getProfession().equals("Director")) {
				if (data.get(i).isIll() == false) {
					counterDir++;
					hourDdi = hourDdi + data.get(i).getHoursD();
					helpHours = data.get(i).getHoursA();
					if (helpHours > maxHourADi) {
						maxHourADi = helpHours;
					}
					helpHours = data.get(i).getHoursT();
					if (helpHours > maxHourTDi) {
						maxHourTDi = helpHours;
					}

				}
			}
		}

		if ((hours * (-1)) > (hourD + hourDdi)) {
			System.out.println("it's not enought hours to substract max hours to substract is " + (hourD + hourDdi));
			return -2;
		}
		helpHours = (hourD + hourDdi + hours);
		if (counterDev > 0) {
			if ((helpHours / counterDev) <= (maxHour - maxHourD)) {
				for (int i = 0; i < data.size(); i++) {
					if (data.get(i).getProfession().equals("Developer")) {
						if (data.get(i).isIll() == false) {
							data.get(i).setHoursD(helpHours / counterDev);
							data.get(i).setDevolopWork(true);
						}
					} else if (data.get(i).getProfession().equals("Director")) {
						data.get(i).setHoursD(0);
						data.get(i).setDevolopWork(false);
					}
				}
				this.setHoursAfterWork();
				return 0;
			} else if (counterDir > 0) {
				helpHoursDivide = (maxHour - maxHourD) * counterDev;
				helpHours = helpHours - helpHoursDivide;
				if ((helpHours / counterDir) <= (744 - maxHourADi - maxHourTDi)) {
					for (int i = 0; i < data.size(); i++) {
						if (data.get(i).getProfession().equals("Developer")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursD(helpHoursDivide / counterDev);
								data.get(i).setDevolopWork(true);
							}
						} else if (data.get(i).getProfession().equals("Director")) {
							if (data.get(i).isIll() == false) {
								data.get(i).setHoursD(helpHours / counterDir);
								data.get(i).setDevolopWork(true);
							}

						}
					}
					this.setHoursAfterWork();
					return 0;
				} else {
					return -1;
				}
			} else {
				return -1;
			}
		} else if (counterDir > 0) {
			if ((helpHours / counterDir) <= (744 - maxHourADi - maxHourTDi)) {
				for (int i = 0; i < data.size(); i++) {
					if (data.get(i).getProfession().equals("Director")) {
						if (data.get(i).isIll() == false) {
							data.get(i).setHoursD(helpHours / counterDir);
							data.get(i).setDevolopWork(true);
						}

					}
				}
				this.setHoursAfterWork();
				return 0;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	public int setWork(int activity, double hours) {
		if (activity == 1) {
			return this.workAssistant(hours);
		} else if (activity == 2) {
			return this.workTechnical(hours);
		} else if (activity == 3) {
			return this.workDeveloper(hours);
		}
		return 0;
	}

	public void setHoursAfterWork() {
		double hoursA = 0, hoursT = 0, hoursD = 0;
		for (int i = 0; i < data.size(); i++) {
			hoursA = data.get(i).getHoursA();
			hoursT = data.get(i).getHoursT();
			hoursD = data.get(i).getHoursD();
			data.get(i).setHours(hoursA + hoursT + hoursD);
		}
	}

	public double getMaxHourForEmployee() {
		double ret = 744, help = 744;
		for (int i = 0; i < data.size(); i++) {
			help = data.get(i).getMaxHours();
			if (help < ret) {
				ret = help;
			}
		}
		return ret;
	}

	public double[] employeeHours(int id) {
		int help;
		double hoursA = 0, hoursT = 0, hoursD = 0;
		help = this.employeeId(id);
		if (help == -1) {
			System.out.println("employee with Id " + id + " doesn't exit");
			double[] ret = { -1 };
			return ret;
		} else {
			hoursA = data.get(help).getHoursA();
			hoursT = data.get(help).getHoursT();
			hoursD = data.get(help).getHoursD();
			double[] ret = { hoursA, hoursT, hoursD };
			return ret;
		}
	}

	public void fireEmployee(int id) {
		int help;
		String name;
		help = this.employeeId(id);
		if (help == -1) {
			System.out.println("Employee with Id " + id + " doesn't exit");
		} else {
			name = data.get(help).getLastName();
			data.remove(help);
			System.out.println("Employee Id " + id + " name " + name + " has been fired");
		}
	}

	public void endIlness(int id) {
		int help;
		help = this.employeeId(id);
		if (help == -1) {
			System.out.println("employee with Id " + id + " doesn't exit");
		} else {
			data.get(help).setIll(false);
			System.out.println("Employee " + id + " " + data.get(help).getLastName() + " has end ilness");
		}
	}

	// ret -1 for adminW fail , -2 for tecgwork fail, -3 for developer fail,
	// -4 for technical and administraion fail,
	// -5 for technic and develop fail, -6 for develop and administration fail,
	// -7 for all fail
	public int reallocation(double hoursA, double hoursT, double hoursD, int prof) {
		int help = 0;
		if (prof == 1) {
			help = this.workAssistant(hoursA);
			if (help == -1) {
				return -1;
			}
		} else if (prof == 2) {
			help = this.workAssistant(hoursA);
			if (help == -1) {
				help = this.workTechnical(hoursT);
				if (help == -1) {
					return -4;
				}
				return -1;
			}
			help = this.workTechnical(hoursT);
			if (help == -1) {
				return -2;
			}

		} else if (prof == 3) {
			help = this.workTechnical(hoursT);
			if (help == -1) {
				help = this.workDeveloper(hoursD);
				if (help == -1) {
					return -5;
				}
				return -2;
			}
			help = this.workDeveloper(hoursD);
			if (help == -1) {
				return -3;
			}
		} else if (prof == 4) {
			help = this.workAssistant(hoursA);
			if (help == -1) {
				help = this.workTechnical(hoursT);
				if (help == -1) {
					help = this.workDeveloper(hoursD);
					if (help == -1) {
						return -7;
					}
				}
				help = this.workDeveloper(hoursD);
				if (help == -1) {
					return -6;
				}
			}
			help = this.workTechnical(hoursT);
			if (help == -1) {
				help = this.workDeveloper(hoursD);
				if (help == -1) {
					return -5;
				}
				return -2;
			}
			help = this.workDeveloper(hoursD);
			if (help == -1) {
				return -3;
			}
		}

		return 0;
	}

	public int startIlness(int id) {
		int help;
		help = this.employeeId(id);
		if (help == -1) {
			System.out.println("employee with Id " + id + " doesn't exit");
		} else {
			data.get(help).setIll(true);
			data.get(help).setAdministrationWork(false);
			data.get(help).setHoursA(0);
			data.get(help).setTechnicalWork(false);
			data.get(help).setHoursT(0);
			data.get(help).setDevolopWork(false);
			data.get(help).setHoursD(0);
			data.get(help).setHours(0);
			System.out.println("Employee " + id + " " + data.get(help).getLastName() + " has started ilness");
		}
		return help;
	}

	@Override
	public int compare(Employee arg0, Employee arg1) {
		return 0;
	}
}
