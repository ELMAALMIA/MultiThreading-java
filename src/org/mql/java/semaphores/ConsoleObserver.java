package org.mql.java.semaphores;

import java.util.Arrays;

public class ConsoleObserver implements Observer {
	private Buffer buffer;

	public ConsoleObserver(Buffer buffer) {
		this.buffer = buffer;
		buffer.add(this);
	}

	@Override
	public void update() {
		System.out.println("WriteHead : " + buffer.getWriteHead() + ", Buffer : " +
			Arrays.toString(buffer.getData())
		);
	}
}
