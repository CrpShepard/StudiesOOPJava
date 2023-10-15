package game.jframe;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.FlowLayout;  
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JPanel{
    JFrame frame;
    JPanel panel;
    
    public GameWindow() {
        frame = new JFrame("GameWindow");   
        panel = new JPanel();

        panel.setLayout(new FlowLayout());

        frame.add(panel);  
        frame.setSize(1024, 768);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);

        System.out.println("GameWindow()");
    }

    @Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GRAY);
		g.fillOval(0, 0, 30, 30);
		g.drawOval(0, 50, 30, 30);		
		g.fillRect(50, 0, 30, 30);
		g.drawRect(50, 50, 30, 30);

		((Graphics2D) g).draw(new Ellipse2D.Double(0, 100, 30, 30));
	}
}
