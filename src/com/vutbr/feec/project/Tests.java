package com.vutbr.feec.project;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {
	Database data = new Database();
	Assistant as1 = new Assistant(1, " ", "Jerred");
	TechnicalWorker tech1 = new TechnicalWorker(2, "Teresa", " ");
	Developer dev1 = new Developer(3, "Zhao Jun", "Kai");
	Assistant as2 = new Assistant(4, "Nico", "Wanjiru");
	TechnicalWorker tech2 = new TechnicalWorker(5, "Etna", "Muscowequan");
	Developer dev2 = new Developer(6, "Markus", "Methodios");
	Assistant as3 = new Assistant(7, "Iason", "Angjelko");
	TechnicalWorker tech3 = new TechnicalWorker(8, "Harold", "Otmar");
	Developer dev3 = new Developer(9, " ", " ");
	Director dir1 = new Director(10, "Alexander", "Amondi");

	@Test
	public void isCapableTest() throws Throwable {
		as1.setHours(5);
		as1.setIll(true);
		as2.setHours(0);
		as3.setHours(5);
		tech1.setHours(5);
		tech1.setIll(true);
		tech2.setHours(0);
		tech3.setHours(5);
		dev1.setHours(5);
		dev1.setIll(true);
		dev2.setHours(0);
		dev3.setHours(5);
		dir1.setHours(5);
		data.data.add(as1);
		data.data.add(as2);
		data.data.add(as3);
		data.data.add(tech1);
		data.data.add(tech2);
		data.data.add(tech3);
		data.data.add(dev1);
		data.data.add(dev2);
		data.data.add(dev3);
		data.data.add(dir1);

		assertEquals("Result be true", true, data.isCapable(1, 4));
		assertEquals("Result be true", true, data.isCapable(4, 4));
		assertEquals("Result be false", false, data.isCapable(7, 4));
		assertEquals("Result be true", true, data.isCapable(2, 4));
		assertEquals("Result be true", true, data.isCapable(5, 4));
		assertEquals("Result be false", false, data.isCapable(8, 4));
		assertEquals("Result be true", true, data.isCapable(3, 4));
		assertEquals("Result be true", true, data.isCapable(6, 4));
		assertEquals("Result be false", false, data.isCapable(9, 4));
		assertEquals("Result be false", false, data.isCapable(10, 4));

	}

	@Test
	public void reverseNameTest() throws Throwable {

		assertEquals("Result be null", null, as1.reverseName());
		assertEquals("Result be null", null, tech1.reverseName());
		assertEquals("Result be iaK nuJ oahZ", "iaK nuJ oahZ", dev1.reverseName());
		assertEquals("Result be soidohteM sukraM", "soidohteM sukraM", dev2.reverseName());
		assertEquals("Result be 3 spaces", "   ", dev3.reverseName());
		assertEquals("Result be idnomA rednaxelA", "idnomA rednaxelA", dir1.reverseName());
	}

	@Test
	public void numberOfVowelsTest() throws Throwable {

		assertEquals("Result be -1 because in assistant method is not implemenst", -1, as1.vowels());
		assertEquals("Result be 3", 3, tech1.vowels());
		assertEquals("Result be 7", 7, tech2.vowels());
		assertEquals("Result be 4", 4, tech3.vowels());
		assertEquals("Result be 5", 5, dev1.vowels());
		assertEquals("Result be 6", 6, dev2.vowels());
		assertEquals("Result be 0", 0, dev3.vowels());
		assertEquals("Result be 7", 7, dir1.vowels());
	}

	@Test
	public void infoAboutEmployeeTest() throws Throwable {
		as2.setIll(true);
		as3.setHours(10);
		tech2.setIll(true);
		tech3.setHours(10.254);
		dir1.setHours(10.256);
		assertEquals("", "name:   Jerred working 0 hours, salary is 500 and is not ill", as1.showEmployeeData());
		assertEquals("", "name: Nico Wanjiru working 0 hours, salary is 500 and is not ill", as2.showEmployeeData());
		assertEquals("", "name: Iason Angjelko working 10 hours, salary is 1500 and is not ill",
				as3.showEmployeeData());
		assertEquals("", "name: Teresa   working 0 hours, salary is 500 and is not ill", tech1.showEmployeeData());
		assertEquals("", "name: Etna Muscowequan working 0 hours, salary is 500 and is not ill",
				tech2.showEmployeeData());
		assertEquals("", "name: Harold Otmar working 10,25 hours, salary is 2050,8 and is not ill",
				tech3.showEmployeeData());
		assertEquals("", null, dev1.showEmployeeData());
		assertEquals("", "name: Alexander Amondi working 10,26 hours, salary is 3589,6 and is not ill",
				dir1.showEmployeeData());

	}

	@Test
	public void showEmployeeTest() {
		for (int i = 1; i < 1001; i++) {
			Assistant as = new Assistant(i, "Nico", "Wanjiru");
			as.setHours(5.268757);
			data.data.add(as);
		}
		for (int i = 1001; i < 2001; i++) {
			TechnicalWorker tech = new TechnicalWorker(i, "Etna", "Muscowequan");
			tech.setHours(5);
			data.data.add(tech);
		}
		for (int i = 2001; i < 3001; i++) {
			Developer dev = new Developer(i, "Markus", "Methodios");
			dev.setHours(5);
			data.data.add(dev);
		}
		dir1.setHours(10);
		data.data.add(dir1);

		assertEquals("data size 1000", 3001, data.data.size());
		assertEquals("salary x", 3043813.55, data.companySalary(), 0.01);
	}
}
