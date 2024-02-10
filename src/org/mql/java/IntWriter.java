package org.mql.java;

import java.util.List;

public class IntWriter extends Thread {
	private List<Integer> sharedResource;

	public IntWriter(String name, List<Integer> shatedResource) {
		super(name);
		this.sharedResource = shatedResource;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			sharedResource.add((int)(Math.random() * 10000));
		}
	}
}
