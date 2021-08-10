package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {		
	
	public static BSTree tree = new BSTree();
	public static int width, height;
	
	public DrawPanel() {
		this.setPreferredSize(new Dimension(1280, 720));

	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		width = getWidth();
		height = getHeight();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		tree.render(g);
		
		
	}
	

}
