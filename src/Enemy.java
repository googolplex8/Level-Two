import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy extends GameObject {
boolean alive = true;
	public Enemy(int x, int y, int width, int height, BufferedImage image) {
		super(x, y, width, height, image);
		// TODO Auto-generated constructor stub
	}

	void update() {
		y+= 2;
		if(y>= 750 + 225 / 4){
			alive = false;
		}
	}

	public void paint(Graphics gra) {
		gra.drawImage(image, x, y, width, height, null);

	}
}
