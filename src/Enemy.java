import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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
		setCollisionBox(new Rectangle(x, y, width, height));

	}

	public void paint(Graphics gra) {
		gra.drawImage(image, x, y, width, height, null);
		gra.setColor(Color.RED);
		//gra.drawRect(x, y, width, height);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
