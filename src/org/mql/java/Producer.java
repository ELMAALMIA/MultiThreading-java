package org.mql.java;

public class Producer extends Thread {
	private Buffer buffer; // Ressource partagée

	public Producer(String name, Buffer buffer) {
		super(name);
		this.buffer = buffer;
	}

	@Override
	public void run() {
		do {
			buffer.write((int)(Math.random() * 9000) + 1000);
		}
		while (true);
	}
}
