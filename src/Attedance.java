import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Attedance {
	JFrame frame;
	JPanel panel;
	JPanel cell[][];
	
	public static void main(String args[]) {
		new Attedance();
	}
	
    public Attedance() {
    	frame = new JFrame();
    	
    	panel = new JPanel();
    	panel.setLayout(new GridLayout(10,3));
    	
    	cell = new JPanel[10][3];
    	Border border = BorderFactory.createLineBorder(Color.black);
    	
    	for(int i=0;i<10;i++) {
    		for(int j=0;j<3;j++) {
    			cell[i][j] = new JPanel();
    			cell[i][j].setBorder(border);
    			panel.add(cell[i][j]);
    			cell[i][j].setBackground(Color.white);
    		}
    	}
    	
    	frame.add(panel,BorderLayout.CENTER);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(500,500);
    	frame.setVisible(true);
    }
}
