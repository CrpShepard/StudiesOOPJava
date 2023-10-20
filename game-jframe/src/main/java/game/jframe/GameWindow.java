package game.jframe;

import javax.swing.*;

import game.jframe.MessageClasses.AllPlayerMoveData;
import game.jframe.MessageClasses.PlayerMoveData;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;

public class GameWindow{
    JFrame frame;
    GamePanel gamePanel;
    private int WindowW = 1024;
    private int WindowH = 768;
    public ArrayList<Boolean> finished = new ArrayList<Boolean>(); // restart => finished

    public int playerId;
    public boolean sentToServer = false;
    public PlayerMoveData playerMoveData;
    
    public GameWindow() {
        frame = new JFrame("GameWindow");
        gamePanel = new GamePanel();
        frame.add(gamePanel);   
        frame.setSize(WindowW, WindowH);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);
    }

    public void setAllPlayerMoveData(AllPlayerMoveData allPlayerMoveData) {
        gamePanel.setAllPlayerMoveData(allPlayerMoveData);
    }

    public void setPlayerMoveData(PlayerMoveData playerMoveData) {
        gamePanel.setPlayerMoveData(playerMoveData);
    }
    class GamePanel extends JPanel {
        private ArrayList<ArrayList<Integer>> unitsCoord = new ArrayList<ArrayList<Integer>>();

        private int playerCount;
        
        private int squareW = 20;
        private int squareH = 20;

        private ArrayList<ArrayList<Integer>> unitsStartCoord = new ArrayList<ArrayList<Integer>>();
        private ArrayList<ArrayList<Integer>> targetsCoord = new ArrayList<ArrayList<Integer>>();
        
        private ArrayList<Float> moveSpeed = new ArrayList<Float>();
        private ArrayList<Integer> duration = new ArrayList<Integer>();
        private ArrayList<Long> startTime = new ArrayList<Long>();
        private ArrayList<Boolean> started = new ArrayList<Boolean>();
        private ArrayList<Double> progress = new ArrayList<Double>();

        private Timer timer;

        public GamePanel() {
            setBorder(BorderFactory.createLineBorder(Color.black));
            //setDefaultValues();

            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    setTarget(playerId, e.getX(), e.getY());
                }
            });

            // Timer timer = new Timer(40, new ActionListener() {
            //     @Override
            //     public void actionPerformed(ActionEvent e) {
            //         ArrayList<Long> curr_duration = new ArrayList<Long>();

            //         for (int i = 0; i < playerCount; i++) {
            //             long time = System.currentTimeMillis();
            //             if (started.get(i) && !finished.get(i)) {
            //                 curr_duration.set(i, time - startTime.get(i));
            //                 if (curr_duration.get(i) > duration.get(i)) {
            //                     curr_duration.set(i, (long) duration.get(i));
            //                     finished.set(i, true);
            //                 }
            //                 progress.set(i, (double)curr_duration.get(i) / (double)duration.get(i));
            //                 moveRect(i);
            //                 repaint();
            //             }
            //         }
            //     }
            // });
            // timer.start();
           
        }

        // void setDefaultValues() {
        //     started.set(playerId, false);
        //     finished.set(playerId, true);
        //     //playerCount = unitsCoord.size();
        //     //sentToServer = false;
        // }

        void setAllPlayerMoveData(AllPlayerMoveData allPlayerMoveData) {
            this.unitsCoord = allPlayerMoveData.playerCoord;
            this.unitsStartCoord = allPlayerMoveData.playerStartCoord;
            this.targetsCoord = allPlayerMoveData.playerTargetCoord;
            this.moveSpeed = allPlayerMoveData.playerMoveSpeed;
            this.startTime = allPlayerMoveData.playerAnimStartTime;
            this.started = allPlayerMoveData.playerAnimStarted;

            playerCount = unitsCoord.size();

            started.set(playerId, false);
            finished.set(playerId, true);

            for (int i = 0; i < playerCount; i++) {
                if (i != playerId) {
                    if (started.get(i)) {
                        findDuration(i);
                        finished.set(i, false);
                    }
                }
            }


            if(timer.isRunning())
                timer.stop();

            timer = new Timer(40, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Long> curr_duration = new ArrayList<Long>();

                    for (int i = 0; i < playerCount; i++) {
                        long time = System.currentTimeMillis();
                        if (started.get(i) && !finished.get(i)) {
                            curr_duration.set(i, time - startTime.get(i));
                            if (curr_duration.get(i) > duration.get(i)) {
                                curr_duration.set(i, (long) duration.get(i));
                                finished.set(i, true);
                            }
                            progress.set(i, (double)curr_duration.get(i) / (double)duration.get(i));
                            moveRect(i);
                            repaint();
                        }
                    }
                }
            });
            timer.start();
        }

        void setPlayerMoveData(PlayerMoveData playerMoveData) {
            unitsCoord.get(playerMoveData.getId()).set(0, playerMoveData.getCoordX());
            unitsCoord.get(playerMoveData.getId()).set(1, playerMoveData.getCoordY());

            unitsStartCoord.get(playerMoveData.getId()).set(0, playerMoveData.getStartCoordX());
            unitsStartCoord.get(playerMoveData.getId()).set(1, playerMoveData.getStartCoordY());

            targetsCoord.get(playerMoveData.getId()).set(0, playerMoveData.getTargetCoordX());
            targetsCoord.get(playerMoveData.getId()).set(1, playerMoveData.getTargetCoordY());

            startTime.set(playerMoveData.getId(), playerMoveData.getAnimStartTime());
            started.set(playerMoveData.getId(), playerMoveData.isAnimStarted());

            findDuration(playerMoveData.getId());
            finished.set(playerMoveData.getId(), false);
        }

        protected void moveRect(int index) {
            int x = (int)Math.round(unitsStartCoord.get(index).get(0) + ((targetsCoord.get(index).get(0) - unitsStartCoord.get(index).get(0)) * progress.get(index)));
            int y = (int)Math.round(unitsStartCoord.get(index).get(1) + ((targetsCoord.get(index).get(1) - unitsStartCoord.get(index).get(1)) * progress.get(index)));

            unitsCoord.get(index).set(0, x);
            unitsCoord.get(index).set(1, y);
        }

        void setTarget(int index, int x, int y) {
            targetsCoord.get(index).set(0, x);
            targetsCoord.get(index).set(1, y);

            unitsStartCoord.get(index).set(0, unitsCoord.get(index).get(0));
            unitsStartCoord.get(index).set(1, unitsCoord.get(index).get(1));

            findDuration(index);
            started.set(index, true);
            startTime.set(index, System.currentTimeMillis());
            finished.set(index, false);

            playerMoveData.setCoordX(unitsCoord.get(index).get(0));
            playerMoveData.setCoordY(unitsCoord.get(index).get(1));
            playerMoveData.setStartCoordX(unitsStartCoord.get(index).get(0));
            playerMoveData.setStartCoordY(unitsStartCoord.get(index).get(1));
            playerMoveData.setTargetCoordX(x);
            playerMoveData.setTargetCoordY(y);
            playerMoveData.setAnimStartTime(startTime.get(index));
            playerMoveData.setAnimStarted(true);
            playerMoveData.setId(playerId);

            sentToServer = true;
        }

        void findDuration(int index) {
            duration.set(index,(int) Math.round(
                Math.sqrt(
                    Math.pow((targetsCoord.get(index).get(0) - unitsCoord.get(index).get(0)), 2) + 
                    Math.pow((targetsCoord.get(index).get(1) - unitsCoord.get(index).get(1)), 2)) / moveSpeed.get(index)));
        }    

        public Dimension getPreferredSize() {
            return new Dimension(WindowW, WindowH);
        }
    
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);       

            for (int i = 0; i < playerCount; i++) {
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
                g.fillRect(unitsCoord.get(i).get(0), unitsCoord.get(i).get(1), squareW, squareH);
                g.setColor(Color.BLACK);
                g.drawRect(unitsCoord.get(i).get(0), unitsCoord.get(i).get(1), squareW, squareH);
            }
        }  
    }
}