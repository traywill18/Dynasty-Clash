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
    Player player = new Player(this, intmap);
    Rect rect1 = new Rect(200,200,129,140);
    Rect rect2 = new Rect(700,200,127,140);
   
    
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
    	    	
    	player.update(); // Update player position
    	
    	
    	  player.boundingBox.x = player.x;
    	    player.boundingBox.y = player.y;

    	    // Update rect1 position to follow the player
    	    rect1.x = player.x + 190;
    	    rect1.y = player.y + 170;

    

    	    // Check for collisions between rect1 and rect2
    	    if (rect1.overlaps(rect2)) {
    	        // Handle collision between rect1 and rect2
    	        // For example, you can move rect1 away from rect2 in each direction
    	        if (rect1.cameFromAbove(rect2)) {
    	            rect1.pushbackFrom(rect2);
    	            System.out.print("collide");
    	        } else if (rect1.cameFromBelow(rect2)) {
    	            rect1.pushbackFrom(rect2);
    	        } else if (rect1.cameFromLeftOf(rect2)) {
    	            rect1.pushbackFrom(rect2);
    	            System.out.print("collide");
    	        } else if (rect1.cameFromRightOf(rect2)) {
    	            rect1.pushbackFrom(rect2);
    	        }
    	    }
     	}
     
		

		
		
   

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;  
        
        tileM.draw(g2);
        player.draw(g2);
        g2.setColor(Color.RED);
        rect1.draw(g2);
        rect2.draw(g2);
       
   
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
