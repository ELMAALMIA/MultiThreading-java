package org.mql.java.semaphores;

public class Producer extends Thread {
	private Buffer buffer; // Ressource partagée
	private Semaphore m, empty, full;

	public Producer(String name, Buffer buffer, Semaphore m, Semaphore empty, Semaphore full) {
		super(name);
		this.buffer = buffer;
		this.m = m;
		this.empty = empty;
		this.full = full;
	}

	@Override
	public void run() {
		do {
			empty.waitDijkstra();
			// Verrouiller()
			m.waitDijkstra();
			buffer.write((int)(Math.random() * 9000) + 1000); // Section  critique
			// Déverrouiller();
			m.signalDijkstra();
			full.signalDijkstra();
		}
		while (true);
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
