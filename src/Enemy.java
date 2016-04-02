import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy extends GameObject {
	private int id = 2;
	public boolean alive = true;

	public Enemy(int x, int y, int width, int height, BufferedImage image, int speed) {
		super(x, y, width, height, image, speed);
		// TODO Auto-generated constructor stub
	}

	void update() {
		y += getSpeed();
		if (y >= 750 + 225 / 4) {
			alive = false;
		}

	}

	public void paint(Graphics gra) {
		gra.drawImage(image, x, y, width, height, null);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
