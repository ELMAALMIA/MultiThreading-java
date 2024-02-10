package org.mql.java.animation;

import java.awt.Graphics;
import java.awt.Image;
//import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Pingouin extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private Thread runner;
	
	private int x, y;
	private int width = 30, height = 30;
	
	private String src[] = {
		"stop", "right1", "right2", "right3", "left1", "left2", "left3"
	};
	private String path = "resources/a01/";
	private String type = ".gif";
	private Image images[];
	private int current = 0, step = 5;
	

	public Pingouin(String name, int x, int y) {
		runner = new Thread(this, name);
		this.x = x;
		this.y = y;
		setLocation(x, y);
		setSize(width, height);
		loadImages();
	}
	
	private void loadImages() {
		images = new Image[src.length];
		for (int i = 0; i < images.length; i++) {
			//images[i] = Toolkit.getDefaultToolkit().createImage(path);
			ImageIcon icon = new ImageIcon(path + src[i] + type);
			images[i] = icon.getImage(); // factoryBean.factoryMethod()
		}
	}
	
	public void start() {
		runner.start();
	}

	@Override
	public void run() {
		do {
			double action = Math.random() * 100;
			if (action < 45) {
				goRight(3);
			}
			else if (action < 90) {
				goLeft(3);
			}
			else {
				stop();
			}
		}
		while (true);
	}
	
	private void goRight(int n) {
		for (int j = 0; j < n; j++) {
			for (int i = 1; i <= 3; i++) {
				current = i;
				x += step;
				setLocation(x, y);
				repaint();
				pause(100);
			}			
		}
	}
	
	private void goLeft(int n) {
		for (int j = 0; j < n; j++) {
			for (int i = 4; i <= 6; i++) {
				current = i;
				x -= step;
				setLocation(x, y);
				repaint();
				pause(100);
			}
		}
	}
	
	private void stop() {
		current = 0;
		repaint();
		pause((int)(Math.random() * 2000) + 1000);
	}
	
	public static void pause(int time) {
		try {
			Thread.sleep(time);
		}
		catch (Exception e) {}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(images[current], 0, 0, null);
	}
}
