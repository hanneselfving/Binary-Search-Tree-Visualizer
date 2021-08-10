package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.SwingWorker;

public class BSTree {

	//Root node
	public static Node root;
	
	public static int depth;
	
	static DrawPanel dPanel;
	
	final static int DELAY = 250;
	
	public BSTree() {
		 
	}
	
	public void render(Graphics g) {
		setAllDrawPositions(root);
		render(g, root);
	}
	
	private void render(Graphics g, Node node) { 
		g.setColor(Color.LIGHT_GRAY);
		if(node == null) {
			return;
		}
		node.render(g);
		
		this.render(g, node.left);
		this.render(g, node.right);	
		
	}
	
	public void setAllDrawPositions(Node node) {
		if(node == null) {
			return;
		}
		
		setDrawPosition(node);
		setAllDrawPositions(node.left);
		setAllDrawPositions(node.right);
		
	}
	
	public void setDrawPosition(Node node) {
		node.drawX = ((node.index * DrawPanel.width) / ((int)Math.pow(2,node.depth) + 1)) - Node.drawSize/2;
		node.drawY = node.depth * DrawPanel.height / (depth+1);
	}
	
	public void addNode(int val) {
	    root = addNode(val, root);
		System.out.println("tree depth is now: " + this.depth);
	}
	
	public boolean seekNode(int val) {
		deselectAll(root);
		Node tempPtr = seekNode(val,root);
		if(tempPtr != null) {
			tempPtr.red = true;
			dPanel.repaint();
			return true;
		}
		dPanel.repaint();
		return false;
	}
	
	private Node seekNode(int val, Node node) {
		
		if(node == null || node.key == val) {
			return node;
		}
		node.blue = true;
		if(val < node.key)
		return seekNode(val, node.left);
		else
		return seekNode(val, node.right);
		
	}
	
	private static void deselectAll(Node node) {
		
		if(node == null)
			return;
		
		node.red = false;
		node.blue = false;
		
		deselectAll(node.left);
		deselectAll(node.right);
		
	}	
	
	public static void levelOrder() { 	
		deselectAll(root);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				int h = depth + 1; 
				int i; 
				for (i=1; i<=h; i++) {
					givenLevel(root, i); 
				}
				return null;
			}

			private void givenLevel (Node node ,int level) 
			{ 
				if (node == null) 
					return; 
				if (level == 1) {
					node.blue = true;
					dPanel.repaint();
					try {
						Thread.sleep(DELAY);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					node.blue = false;
				}
				else if (level > 1) 
				{ 
					givenLevel(node.left, level-1); 
					givenLevel(node.right, level-1); 
				} 
			} 

		};
		worker.execute();
		dPanel.repaint();
	} 
	
	public static void inOrder() {
	    deselectAll(root);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				
				
				inOrder(root);
				
				return null;
			}
			
			private void inOrder(Node node) {
				if(node == null) {
					return;
				}
				inOrder(node.left);
				node.blue = true;
				dPanel.repaint();
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				node.blue = false;
				inOrder(node.right);
				
			}
			
		};
		worker.execute();	
		dPanel.repaint();
	}
	
	public static void preOrder() {
		deselectAll(root);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
		
				preOrder(root);
				
				return null;
			}
			
			private void preOrder(Node node) {
				if(node == null) {
					return;
				}
				
				node.blue = true;
				dPanel.repaint();
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				node.blue = false;
				preOrder(node.left);
				preOrder(node.right);
			}
			
		};
		worker.execute();	
		dPanel.repaint();
	}
	
	public static void postOrder() {
		 deselectAll(root);
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				
				
				postOrder(root);
				
				return null;
			}
			
			private void postOrder(Node node) {
				if(node == null) {
					return;
				}
				
		
				postOrder(node.left);
				postOrder(node.right);
				
				node.blue = true;
				dPanel.repaint();
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				node.blue = false;
				
			}
			
		};
		worker.execute();	
		dPanel.repaint();
	}
	
	private Node addNode(int val, Node node) { 
		
	    if (node == null) {
	        return new Node(val);  
	    }
	    if (val < node.key) {
	       node.left = addNode(val, node.left);
	       node.left.depth = node.depth + 1;
	       if(node.left.depth > this.depth) {
		    	depth = node.left.depth;
		    }
	       node.left.parent = node;
	       node.left.index = (node.index*2)-1;	       
	    } else {
	       node.right = addNode(val, node.right);
	       node.right.depth = node.depth + 1;
	       if(node.right.depth > this.depth) {
		    	depth = node.right.depth;
		    }
	       node.right.parent = node;
	       node.right.index = (node.index*2);
	    } 
	    node.height = max( height( node.left ), height( node.right ) ) + 1;
	    node.size++;
	    return node;
	}
	
	private int height(Node n) {
		
		return n == null ? -1 : n.height;
		
	}
	
	private int max(int a, int b) {
		return a > b ? a : b; 
	}

	public boolean isEmpty() {
        return root == null;
    }
	
    /**
     * Returns the number of nodes in the tree.
     * 
     * @return the number of nodes in the tree
     */
    public int size() {
        return size(root);
    }
    
    /**
     * Returns the number of nodes in the subtree.
     * 
     * @param x the subtree
     * 
     * @return the number of nodes in the subtree
     */
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }
    
    public static void setAllDrawSizes(int val) {
    	setAllDrawSizes(val, root);	
    	dPanel.repaint();
    }
    
    private static void setAllDrawSizes(int val, Node node) {
    	if(node == null) {
			return;
		}
		
		Node.drawSize = val;
		setAllDrawSizes(val, node.left);
		setAllDrawSizes(val, node.right);
		
    }
    
    
	
}
