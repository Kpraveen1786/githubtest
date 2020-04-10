package com.forsys.duma;

import org.testng.TestNG;

public class TestRunner {

	static TestNG testNg;
	public static String path = "";

	public static void main(String[] args) throws ClassNotFoundException {

		testNg = new TestNG();
		System.out.println("main() ");

		// Class c = Class.forName(args[0]);
		// testNg.setTestClasses(new Class[] { c });
		path = args[0];
		System.out.println(path);
		testNg.setTestClasses(new Class[] { com.forsys.duma.Test_Data.class });
		testNg.run();

	}

}
