import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class GameObject {
	private int id;
	int x;
	int y;
	int width;
	int height;
	private int speed;
	private Rectangle collisionBox;
	BufferedImage image;

	public GameObject(int x, int y, int width, int height, BufferedImage image, int speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
		setSpeed(speed);
		setCollisionBox(new Rectangle(x, y, width, height));
	}

	void update() {

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x1) {
		x = x1;
		getCollisionBox().setLocation(x1, (int) getCollisionBox().getY());
	}

	public void setY(int y1) {
		y = y1;
		getCollisionBox().setLocation((int) getCollisionBox().getX(), y1);
		
	}

	public void paint(Graphics gra) {
		gra.drawImage(image, x, y, width, height, null);

	}

	Rectangle getCollisionBox() {
		return collisionBox;
	}

	void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	int getSpeed() {
		return speed;
	}

	void setSpeed(int speed) {
		this.speed = speed;
	}

}
