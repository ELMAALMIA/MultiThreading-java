package org.mql.java.semaphores;

import javax.swing.JFrame;

public class ProducerConsumer extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Buffer buffer;
	private Producer producers[];
	private Consumer consumers[];
	private Semaphore m, empty, full;
	private PanelObserver observer;

	public ProducerConsumer() {
		init();
		userInterface();
		createProducers(3);
		createConsumers(2);
	}
	
	private void init() {
		buffer = new Buffer(10);
		m = new Semaphore("Exclusion Mutuelle");
		empty = new Semaphore("Vide", 10);
		full = new Semaphore("Plein", 0);
		//m.setLogger(new ConsoleLogger());
		//m.setLogger(new FileLogger("resources/logs/reader-writer.log"));
		//full.setLogger(new ConsoleLogger());
		
		//buffer.setLogger(new ConsoleLogger());
		new ConsoleObserver(buffer);
		observer = new PanelObserver(buffer, 60);
	}
	
	private void userInterface() {
		setContentPane(observer);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void createProducers(int n) {
		producers = new Producer[n];
		for (int i = 0; i < n; i++) {
			producers[i] = new Producer("P" + i, buffer, m, empty, full);
			producers[i].start();
		}
	}
	
	private void createConsumers(int n) {
		consumers = new Consumer[n];
		for (int i = 0; i < n; i++) {
			consumers[i] = new Consumer("C" + i, buffer, m, empty, full);
			consumers[i].start();
		}
	}

	public static void main(String[] args) {
		new ProducerConsumer();
	}
}
