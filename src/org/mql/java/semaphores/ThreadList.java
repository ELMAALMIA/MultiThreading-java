package org.mql.java.semaphores;

import java.util.LinkedList;
import java.util.List;

/*
 * File d'attente de processus
 */
public class ThreadList {
	private String name;
	private List<Thread> list;
	
	public ThreadList(String name) {
		this.name = name;
		list = new LinkedList<Thread>();
	}

	synchronized public void add(Thread p) {
		list.add(p);
	}
	
	synchronized public void remove(Thread p) {
		list.remove(p);
	}
	
	public List<Thread> getList() {
		return list;
	}
	
	public String getName() {
		return name;
	}
}
