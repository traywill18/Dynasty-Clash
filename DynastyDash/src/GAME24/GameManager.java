// GameManager.java
package GAME24;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameManager extends JPanel implements Runnable {

    public static final int HEIGHT = 1920; // Adjusted height for demo purposes
    public static final int WIDTH  = 1080; // Adjusted width for demo purposes
    public static final String TITLE = "Dynasty_Dash_Beta";

    
    //Instantiate
    TileManager tileM = new TileManager(this);
    InputMapper intmap = new InputMapper();
    Player player = new Player(this,intmap);
    
    
    
    int FPS = 60;
    private Thread thread;
    private boolean running = false;

    

    public GameManager() {
        // Add the InputMapper as a KeyListener to listen for key events
        this.addKeyListener(intmap);
        // Set focusable to true so that the JPanel can receive key events
        this.setFocusable(true);
        // Start listening for key events
        this.requestFocusInWindow();
    }

    public void stop(){
        if (!running)
            return;
        running  = false;
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(running) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
    	
       player.update(); // calling this method in the player class
       
       
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;  
        
        tileM.draw(g2);
        player.draw(g2);
        
        
        
        g2.dispose();
       
    }

    public static void main(String[] args) {
        GameManager game = new GameManager();
        JFrame frame = new JFrame();
        frame.add(game);
        frame.pack();
        frame.setTitle(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);

        game.running = true;
        game.thread = new Thread(game);
        game.thread.start();
        System.out.println("Running...");
    }
}
