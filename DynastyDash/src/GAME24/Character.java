package GAME24;

import java.awt.image.BufferedImage;

// this class will store function that will used for the player, enemy and NPC CLASS

public class Character {
	
	public int x,y;
	
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1 ,left2, right1 ,right2;
	public String direction;

	public Rect boundingBox;
}
