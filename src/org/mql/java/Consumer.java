package org.mql.java;

public class Consumer extends Thread {
	private Buffer buffer;

	public Consumer(String name, Buffer buffer) {
		super(name);
		this.buffer = buffer;
	}

	@Override
	public void run() {
		do {
			buffer.read();
		}
		while (true);
	}
}
