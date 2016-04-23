import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Truck extends GameObject {
	public Boolean left;
	public Boolean right;
	public int id = 0;

	public Truck(int x, int y, int width, int height, BufferedImage image, int speed) {
		super(x, y, width, height, image, speed);
		left = false;
		right = false;
		// TODO Auto-generated constructor stub
	}

	void update() {
		if (left == true) {
			int newX = this.getX();
			this.setX(newX -= getSpeed());
		}

		if (right == true) {
			int newX = this.getX();
			this.setX(newX += getSpeed());
		}
		if (this.getX() <= 150) {
			this.setX(150);
		}
		if (this.getX() >= 812 - 156) {
			this.setX(812 - 156);
		}
		setCollisionBox(new Rectangle(x, y, width, height));
	}

	public void paint(Graphics gra) {
		gra.drawImage(image, x, y, width, height, null);
		//gra.drawRect(x, y, width, height);

	}

}
