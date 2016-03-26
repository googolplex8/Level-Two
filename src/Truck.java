import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Truck extends GameObject {
	public Boolean left;
	public Boolean right;

	public Truck(int x, int y, int width, int height, BufferedImage image) {
		super(x, y, width, height, image);
		left = false;
		right = false;
		// TODO Auto-generated constructor stub
	}

	void update() {
		if (left == true) {
			int newX = this.getX();
			this.setX(newX -= 5);
		}

		if (right == true) {
			int newX = this.getX();
			this.setX(newX += 5);
		}
		if (this.getX() <= 150) {
			this.setX(150);
		}
		if (this.getX() >= 812 - 156) {
			this.setX(812 - 156);
		}
	}

	public void paint(Graphics gra) {
		gra.drawImage(image, x, y, width, height, null);

	}

}
