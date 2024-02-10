package org.mql.java;

public class Processus extends Thread {

	public Processus(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		do {
			System.out.println(getName());
			try {
				Thread.sleep(100);
			}
			catch(Exception e) {}
		}
		while(true);
	}

}
