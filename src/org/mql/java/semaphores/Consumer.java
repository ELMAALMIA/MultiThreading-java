package org.mql.java.semaphores;

public class Consumer extends Thread {
	private Buffer buffer; // Ressource partagée
	private Semaphore m, empty, full;

	public Consumer(String name, Buffer buffer, Semaphore m, Semaphore empty, Semaphore full) {
		super(name);
		this.buffer = buffer;
		this.m = m;
		this.empty = empty;
		this.full = full;
	}

	@Override
	public void run() {
		do {
			full.waitDijkstra();
			// Verrouiller()
			m.waitDijkstra();
			buffer.read(); // Section  critique
			// Déverrouiller();
			m.signalDijkstra();
			empty.signalDijkstra();
		}
		while (true);
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
