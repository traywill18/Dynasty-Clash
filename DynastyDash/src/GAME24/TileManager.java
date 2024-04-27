package GAME24;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TileManager {

	GameManager gm;
	Tiles[] tiles;
	
	public TileManager (GameManager gm) {
		this.gm = gm;
		
		tiles = new Tiles[10];
		getTilesImage();
	}
	
	public void getTilesImage() {
		
		try {
			
			tiles[0] = new Tiles();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/moon.png"));

			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void draw(Graphics2D g2) {
		
		g2.drawImage(tiles[0].image, 0, 0, 1920, 1080, null);
	}
}
