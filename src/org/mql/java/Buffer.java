package org.mql.java;

import static org.mql.java.Context.*;

/*
 * Tampon partagé => Shared Resource
 */
public class Buffer {
	private Object[] data;
	private int readHead = 0;
	private int writeHead = 0;

	public Buffer(int size) {
		data = new Object[size];
	}

	/*
	 * Section Critique => Nécessité d'Exclusion Mutuelle (Accès Exclusif)
	 * on parle aussi d'accès synchronisé (au contaire d'un accès 
	 * Simultané qui a lieu par défaut) 
	 * ==> Solution (Macanisme) de synchronisation
	 * ==> 2 (principale) solutions existent :
	 *  	1. Moniteurs
	 *  	2. Sémaphores
	 */
	synchronized public void write(Object element) {
		print("Début Ecriture");
		data[writeHead] = element;
		writeHead = (writeHead < data.length - 1) ? writeHead + 1 : 0;
		pause(1000);
		print("Fin Ecriture");
	}
	
	// Une autre Section Critique
	synchronized public Object read() {
		print("Début Lecture");
		Object element = data[readHead];
		data[readHead] = null;
		readHead = (readHead < data.length - 1) ? readHead + 1 : 0;
		pause(1000);
		print("Fin Lecture");
		return element;
	}
	
	synchronized public Object[] getData() {
		return data;
	}
	
}
