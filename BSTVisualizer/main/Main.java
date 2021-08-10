package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class Main extends JFrame {

	public static Main mainFrame;
	public static DrawPanel panel;
	public static OptionsPanel options;
	
	Main() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		
		mainFrame = new Main();
		panel = new DrawPanel();
		options = new OptionsPanel();
		OptionsPanel.dPanel = panel;
		BSTree.dPanel = panel;
		
		mainFrame.add(panel, BorderLayout.CENTER);
		mainFrame.add(options, BorderLayout.NORTH);
		mainFrame.pack();
		DrawPanel.width = panel.getWidth();
		DrawPanel.height = panel.getHeight();
		mainFrame.setVisible(true);
		
		

	}

}
