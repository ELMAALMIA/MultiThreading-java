package org.mql.java.animation;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Examples extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel screen;

	public Examples() {
		userInterface();
	}

	private void userInterface() {
		screen = new JPanel();
		screen.setLayout(null);
		screen.setBackground(Color.black);
		screen.addMouseListener(new ScreenListener());
		setContentPane(screen);
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);
	}

	void exp01() {
		Task p1 = new Task("p1");
		Task p2 = new Task("p2");
		p1.start();
		p2.start();
	}

	public static void main(String[] args) {
		new Examples();
	}

	class ScreenListener implements MouseListener {
		private int counter = 0;

		@Override
		public void mouseClicked(MouseEvent e) {
			// System.out.println("Click sur : " + e.getX() + ", " + e.getY());
			counter++;
			Pingouin p = new Pingouin("P" + counter, e.getX(), e.getY());
			screen.add(p);
			p.start();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
