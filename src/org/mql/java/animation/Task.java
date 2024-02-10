package org.mql.java.animation;

public class Task implements Runnable {
	private Thread runner;

	public Task(String name) {
		runner = new Thread(this, name);
	}
	
	public void start() {
		runner.start();
	}

	@Override
	public void run() {
		do {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			}
			catch (Exception e) {}
		}
		while (true);
	}

}
