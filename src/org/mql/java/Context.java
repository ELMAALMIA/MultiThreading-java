package org.mql.java;

public class Context {

	public static void print(String msg) {
		System.out.println("> " + msg + ", Par : " + Thread.currentThread().getName());
	}
	
	public static void pause(int time) {
		try {
			Thread.sleep(time);
		}
		catch (Exception e) {}
	}

}
