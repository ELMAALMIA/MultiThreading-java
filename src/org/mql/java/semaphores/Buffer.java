package org.mql.java.semaphores;

import static org.mql.java.Context.*;

import java.util.List;
import java.util.Vector;

public class Buffer {
	private Object[] data;
	private int readHead = 0;
	private int writeHead = 0;
	
	private Logger logger;
	private List<Observer> observers;
	private int currentWritePosition = 0, currentReadPosition = 0;
	private Thread currentProducer = null, currentConsumer = null;
	private Object element;

	public Buffer(int size) {
		data = new Object[size];
		observers = new Vector<Observer>();
	}
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	private void log(String msg) {
		if (logger != null) logger.log("info", msg + ", par : " + Thread.currentThread());
	}
	
	public void add(Observer observer) {
		observers.add(observer);
	}
	
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	public void write(Object element) {
		log("Début Ecriture");
		currentWritePosition = writeHead;
		currentProducer = Thread.currentThread();
		data[writeHead] = element;
		writeHead = (writeHead < data.length - 1) ? writeHead + 1 : 0;
		notifyObservers();
		pause(1000);
		currentProducer = null;
		log("Fin Ecriture");
	}
	
	public Object read() {
		log("Début Lecture");
		currentReadPosition = readHead;
		currentConsumer = Thread.currentThread();
		element = data[readHead];
		data[readHead] = null;
		readHead = (readHead < data.length - 1) ? readHead + 1 : 0;
		notifyObservers();
		pause(1000);
		currentConsumer = null;
		log("Fin Lecture");
		return element;
	}
	
	public Object[] getData() {
		return data;
	}
	
	public int getReadHead() {
		return readHead;
	}
	
	public int getWriteHead() {
		return writeHead;
	}
	
	public Thread getCurrentConsumer() {
		return currentConsumer;
	}
	
	public Thread getCurrentProducer() {
		return currentProducer;
	}
	
	public int getCurrentReadPosition() {
		return currentReadPosition;
	}
	
	public int getCurrentWritePosition() {
		return currentWritePosition;
	}
	
	public Object getElement() {
		return element;
	}
}
