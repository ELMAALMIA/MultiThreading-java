package org.mql.java.semaphores;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelObserver extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	
	private Buffer buffer;
	private int margin = 50, size, length;

	public PanelObserver(Buffer buffer, int size) {
		this.buffer = buffer;
		buffer.add(this);
		this.size = size;
		this.length = buffer.getData().length;
	}

	@Override
	public void update() {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.white);
		g.fillRect(margin, margin, length * size,  size);
		g.setColor(Color.blue);
		g.drawRect(margin, margin, length * size,  size);
		
		for (int i = 1; i < length; i++) {
			g.drawLine(i * size + margin, margin, i * size + margin, margin + size);
		}
		
		Object data[] = buffer.getData();
		g.setFont(new Font("Segoe UI", Font.BOLD, 16));
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null) {
				g.setColor(Color.green);
				g.fillRect(i * size + margin + 2, margin + 2, size - 3, size - 3);
				g.setColor(Color.blue);
				g.drawString("" + data[i], i * size + margin + 10, margin + size / 2);
			}
		}
		
		Thread p = buffer.getCurrentProducer();
		if (p != null) {
			int i = buffer.getCurrentWritePosition();
			g.setColor(Color.blue);
			g.fillOval(i * size + margin + size / 2, margin - 15, 10, 10);
			g.drawString(p.getName(), i * size + margin + size / 2, margin - 18);
		}
		Thread c = buffer.getCurrentConsumer();
		if (c != null) {
			int i = buffer.getCurrentReadPosition();
			g.setColor(Color.red);
			g.fillOval(i * size + margin + size / 2, margin + size + 5, 10, 10);
			String s = c.getName() + "(" + buffer.getElement() + ")";
			g.drawString(s, i * size + margin + size / 2, margin + size  + 30);
		}
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(length * size + 2 * margin, size + 2 * margin);
	}
}
