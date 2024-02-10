package org.mql.java.semaphores;

/*
 * Créée en 1965 par Dijkstra
 */
public class Semaphore {
	private String name;
	private int value;
	private ThreadList waitingThreadList; // File d'attente des processus bloqués 
	
	private Logger logger = null;

	public Semaphore(String name) {
		this.name = name;
		init(1); // Sémaphore d'Exclusion Mutuelle
	}
	
	public Semaphore(String name, int value) {
		this.name = name;
		init(value);
	}
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	// primitives de Dijkstra :
	// => Section de code indivisible
	synchronized public void init(int value) {
		this.value = value;	
		waitingThreadList = new ThreadList(name);
	}
	
	synchronized public void waitDijkstra() {
		value--;
		if (value < 0) {
			suspend();
		}
	}
	
	synchronized public void signalDijkstra() {
		value++;
		if (value <= 0) {
			resume();
		}
	}
	
	private void suspend() { // bloquer()
		try {
			waitingThreadList.add(Thread.currentThread());
			log("info", Thread.currentThread() + " se bloque => "
						+ waitingThreadList.getList());
			wait();
			waitingThreadList.remove(Thread.currentThread());
			log("info", Thread.currentThread() + " se réveille => "
					+ waitingThreadList.getList());
		}
		catch (Exception e) {}		
	}
	
	private void resume() {
		log("info", Thread.currentThread() + " envoie une notification!");
		notify();
	}
	
	private void log(String level, String msg) {
		if (logger != null) {
			logger.log(level, msg);
		}
	}
}
