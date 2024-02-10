package org.mql.java;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Vector;

public class Examples {

	public Examples() {
		exp03();
	}
	
	void exp01() {
		Processus p1 = new Processus("p1");
		Processus p2 = new Processus("p2");
		p1.start();
		p2.start();
	}
	
	void exp02() {
		/*
		 * Vector est une structure Thread-safe
		 * ArrayList et LinkedList : Not Thread-safe
		 */
		List<Integer> sharedResource = new Vector<Integer>();
		IntWriter w1 = new IntWriter("w1", sharedResource);
		IntWriter w2 = new IntWriter("w2", sharedResource);
		
		w1.start();
		w2.start();
		
		try {
			w1.join();
			w2.join();
			
			System.out.println(sharedResource.size());
		}
		catch (Exception e) {}
	}

	void exp03() {
		Buffer buffer = new Buffer(10);
		Producer p[] = new Producer[2];
		for (int i = 0; i < p.length; i++) {
			p[i] = new Producer("P" + i, buffer);
			p[i].start();
		}
		Consumer c[] = new Consumer[0];
		for (int i = 0; i < c.length; i++) {
			c[i] = new Consumer("C" + i, buffer);
			c[i].start();
		}
		
		do {
			System.out.println(Arrays.toString(buffer.getData()));
			Context.pause(1000);
		}
		while (true);
	}
	
	public static void main(String[] args) {
		new Examples();
	}
}
