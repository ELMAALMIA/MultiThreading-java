package org.mql.java.semaphores;

public class ConsoleLogger implements Logger {

	public ConsoleLogger() {
	}

	@Override
	public void log(String level, String msg) {
		System.out.println("### " + level + " : " + msg);
	}

}
