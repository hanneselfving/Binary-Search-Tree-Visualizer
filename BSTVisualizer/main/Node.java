package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Node {
	
    final int key;   // the key
    int depth = 0;      // vertical position
    int index = 1;		// horizontal position
    int height = 0;
    int size = 0;        // number of nodes in subtree
    
    boolean red = false;
    boolean blue = false;
   
    Node left;       // left subtree
    Node right;      // right subtree
    Node parent;
    
    int drawX,drawY;
    static int drawSize = OptionsPanel.scaleSlide.getValue();
    
    Font myFont = new Font("Serif", Font.BOLD, 12);

    public Node(int key) {
        this.key = key;
    }
    
    public void render(Graphics g) {

    	Graphics2D g2 = (Graphics2D) g;
    	RenderingHints qualityHints = new RenderingHints(
    			  RenderingHints.KEY_ANTIALIASING,
    			  RenderingHints.VALUE_ANTIALIAS_ON);
    			qualityHints.put(
    			  RenderingHints.KEY_RENDERING,
    			  RenderingHints.VALUE_RENDER_QUALITY );
    			g2.setRenderingHints( qualityHints );
    	
    	g2.setColor(Color.LIGHT_GRAY);
    	g2.fillOval(drawX, drawY, drawSize, drawSize);
    	
    	g2.setStroke(new BasicStroke(5));
    	if(blue) {
    	  	g2.setColor(Color.BLUE);
        	g2.drawOval(drawX, drawY, drawSize, drawSize);
    	}
    	if(red) {
	    	g2.setColor(Color.RED);
	    	g2.drawOval(drawX, drawY, drawSize, drawSize);
    	}
    	
    	g2.setStroke(new BasicStroke(3));
    	g2.setColor(Color.LIGHT_GRAY);
    	if(left != null)
    	g2.drawLine(this.drawX + drawSize/2, this.drawY + drawSize/2, left.drawX + drawSize/2, left.drawY + drawSize/2);
    	if(right != null)
    	g2.drawLine(this.drawX + drawSize/2, this.drawY + drawSize/2, right.drawX + drawSize/2, right.drawY + drawSize/2);
    	
    	g2.setColor(Color.BLACK);
    	myFont = myFont.deriveFont(Font.BOLD, drawSize/2);
    	g2.setFont(myFont);
    	g2.drawString(""+key, drawX + drawSize/4 , drawY+ drawSize/2);
    	
    	if(OptionsPanel.infoBtn.isSelected()) {
    	myFont = myFont.deriveFont(Font.BOLD, 10);
    	g2.setColor(Color.WHITE);
    	g2.drawString("Size: " + this.size, drawX + drawSize/3 + 20, drawY + drawSize/4);
    	g2.drawString("Depth: " + this.depth, drawX + drawSize/3 + 20, drawY + drawSize/4 + 15);    		
    	g2.drawString("Height: " + this.height, drawX + drawSize/3 + 20, drawY + drawSize/3 + 30);
    	g2.drawString("Index: " + this.index, drawX + drawSize/3 + 20, drawY + drawSize/2 + 45);
    	}
    		
    }

}
