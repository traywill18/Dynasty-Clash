package GAME24;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player extends Character {
	
	GameManager gm;
	InputMapper intmap;  
	
	// adding the constructor 

	public Player(GameManager gm , InputMapper intmap) {
		
		this.gm = gm;
		this.intmap = intmap;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	// setting the default values for the player 
	public void setDefaultValues() {
		x     = 100;
		y     = 100;
		speed = 7;
		direction = "left";
	}
	
	public void getPlayerImage() {
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/MK1_RUN.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/MK2_RUN.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/MK3_RUN.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/MK4_RUN.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/MK9_RUN.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/MK10_RUN.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/MK7_RUN.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/MK8_RUN.png"));
		
			
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		 if(intmap.upPressed == true) {
			 direction = "up";
			 y -= speed;
			 
		 } else if(intmap.downPressed == true) {
			 direction = "down";
			 y += speed;
			 
		 }else if(intmap.leftPressed == true) {
			 direction = "left";
			 x -= speed;
			 
		 }else if(intmap.rightPressed == true) {
			 direction = "right";
			 x += speed;
		 }
		 }
	
	public void draw(Graphics g2) {
	    BufferedImage image = null;
	    
	    switch(direction) {
	        case "up":
	            image = (y % 2 == 0) ? up1 : up2; // Alternate between two images for animation
	            break;
	        case "down":
	            image = (y % 2 == 0) ? down1 : down2;
	            break;
	        case "left":
	            image = (x % 2 == 0) ? left1 : left2;
	            break;
	        case "right":
	            image = (x % 2 == 0) ? right1 : right2;
	            break;
	    }
	    
	    g2.drawImage(image, x, y, 500, 500, null); // Draw the image at player position
	}

}
