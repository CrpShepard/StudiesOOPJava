package game.jframe;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;

public class GameWindow{
    JFrame frame;
    GamePanel gamePanel;
    private int WindowW = 1024;
    private int WindowH = 768;
    public int unitAmount;
    public boolean[] restart = new boolean[unitAmount];

    public int playerId;
    
    public GameWindow() {
        frame = new JFrame("GameWindow");
        gamePanel = new GamePanel();
        frame.add(gamePanel);   
        frame.setSize(WindowW, WindowH);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);
    }

    public void setTarget(int index, int x, int y) {
        gamePanel.setTarget(index, x, y);
    }

    class GamePanel extends JPanel {
        private int[][] unitsCoord = new int[unitAmount][2];
        
        private int squareW = 20;
        private int squareH = 20;

        private int[][] unitsStartCoord = new int[unitAmount][2];
        private int[][] targetsCoord = new int[unitAmount][2];
        
        private double[] moveSpeed = new double[unitAmount];
        private int[] duration = new int[unitAmount];
        private long[] startTime = new long[unitAmount];
        private boolean[] started = new boolean[unitAmount];
        private double[] progress = new double[unitAmount];

        public GamePanel() {
            setBorder(BorderFactory.createLineBorder(Color.black));
            setDefaultValues();
            Timer timer = new Timer(40, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long[] curr_duration = new long[unitAmount];

                    for (int i = 0; i < unitAmount; i++) {
                        if (!started[i]) {
                            startTime[i] = System.currentTimeMillis();
                            started[i] = true;
                        }
                        long time = System.currentTimeMillis();
                        curr_duration[i] = time - startTime[i];
                        if (curr_duration[i] > duration[i]) {
                            curr_duration[i] = duration[i];
                            restart[i] = true;
                        }
                        progress[i] = (double)curr_duration[i] / (double)duration[i];
                        moveRect(i);
                        repaint();
                    }
                }
            });
            timer.start();

            // addMouseListener(new MouseAdapter() { // на будущее
            //     public void mousePressed(MouseEvent e) {
            //         moveSquare(e.getX(),e.getY());
            //     }
            // });

            // addMouseMotionListener(new MouseAdapter() {
            //     public void mouseDragged(MouseEvent e) {
            //         moveSquare(e.getX(),e.getY());
            //     }
            // });
        }

        void setDefaultValues() {
            for (int i = 0; i < unitAmount; i++) { // UnitsCoord, moveSpeed, started, restart
                int x = ThreadLocalRandom.current().nextInt(0, WindowW);
                int y = ThreadLocalRandom.current().nextInt(0, WindowH);
                unitsCoord[i][0] = x;
                unitsCoord[i][1] = y;

                moveSpeed[i] = 0.1f;
                started[i] = false;
                restart[i] = true;
            }
        }

        protected void moveRect(int index) {
            int x = (int)Math.round(unitsStartCoord[index][0] + ((targetsCoord[index][0] - unitsStartCoord[index][0]) * progress[index]));
            int y = (int)Math.round(unitsStartCoord[index][1] + ((targetsCoord[index][1] - unitsStartCoord[index][1]) * progress[index]));

            unitsCoord[index][0] = x;
            unitsCoord[index][1] = y;
        }

        void setTarget(int index, int x, int y) {
            targetsCoord[index][0] = x;
            targetsCoord[index][1] = y;

            unitsStartCoord[index][0] = unitsCoord[index][0];
            unitsStartCoord[index][1] = unitsCoord[index][1];

            findDuration(index);
            started[index] = false;
            restart[index] = false;

            System.out.println("New target for unit " + index + " X: " + targetsCoord[index][0] + " Y: " + targetsCoord[index][1]);
        }

        void findDuration(int index) {
            duration[index] = (int) Math.round(
                Math.sqrt(
                    Math.pow((targetsCoord[index][0] - unitsCoord[index][0]), 2) + 
                    Math.pow((targetsCoord[index][1] - unitsCoord[index][1]), 2)) / moveSpeed[index]);
        }
    
        // private void moveSquare(int x, int y) {
        //     int OFFSET = 1;
        //     if ((squareX!=x) || (squareY!=y)) {
        //         repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
        //         squareX=x;
        //         squareY=y;
        //         repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
        //     } 
        // }
    

        public Dimension getPreferredSize() {
            return new Dimension(WindowW, WindowH);
        }
    
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);       

            for (int i = 0; i < unitAmount; i++) {
                Color color;

                switch (i){
                    case 0: color = Color.RED; break;
                    case 1: color = Color.BLUE; break;
                    case 2: color = new Color(207, 241, 190); break;
                    case 3: color = new Color(207, 245, 80); break;
                    case 4: color = Color.YELLOW; break;
                    case 5: color = Color.ORANGE; break;
                    case 6: color = Color.GREEN; break;
                    case 7: color = Color.PINK; break;
                    case 8: color = Color.GRAY; break;
                    case 9: color = new Color(207, 247, 235); break;
                    case 10: color = new Color(207, 241, 6); break;
                    case 11: color = new Color(207, 244, 242); break;
                    default: color = Color.WHITE; break;
                }

                g.setColor(color);
                g.fillRect(unitsCoord[i][0], unitsCoord[i][1], squareW, squareH);
                g.setColor(Color.BLACK);
                g.drawRect(unitsCoord[i][0], unitsCoord[i][1], squareW, squareH);
            }
        }  
    }
}