package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarInputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionsPanel extends JPanel {
	
	static JButton btnAdd = new JButton("Add Node");
	static JButton btnSearch = new JButton("Find Node");
	static JLabel travText = new JLabel("Traversals: ");
	static JButton btnIn = new JButton("Inorder");
	static JButton btnPre = new JButton("Preorder");
	static JButton btnPost = new JButton("Postorder");
	static JButton btnBreadth = new JButton("Breadth");
	static JCheckBox infoBtn = new JCheckBox("Toggle Info");
	
	static final int orgScaleVal = 20;
	static JSlider scaleSlide = new JSlider();
	static JLabel scaleLabel = new JLabel("Scale");
	
	static DrawPanel dPanel;
	
	OptionsPanel() {
		
		
		add(btnAdd);
		add(btnSearch);
		add(travText);
		add(btnIn);
		add(btnPre);
		add(btnPost);
		add(btnBreadth);
		
		scaleSlide.setMinimum(10);
		scaleSlide.setValue(orgScaleVal);
		add(scaleLabel);
		add(scaleSlide);
		
		add(infoBtn);
		
		initListeners();
		
	}
	
	private void initListeners() {
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int val = Integer.parseInt((JOptionPane.showInputDialog("Enter value")));
				JOptionPane.showMessageDialog(dPanel, "" + DrawPanel.tree.seekNode(val));
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int val = Integer.parseInt((JOptionPane.showInputDialog("Enter value")));
				DrawPanel.tree.addNode(val);
				dPanel.repaint();
			}
		});
		
		scaleSlide.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				BSTree.setAllDrawSizes(scaleSlide.getValue());
				
				
			}
		});
		
		infoBtn.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
					dPanel.repaint();
				
			}
		});
		
		btnIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BSTree.inOrder();
				
			}
		});
		
		btnPre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BSTree.preOrder();
				
			}
		});
		
		btnPost.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BSTree.postOrder();
				
			}
		});
		
		btnBreadth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BSTree.levelOrder();
				
			}
		});
		
	}

}
