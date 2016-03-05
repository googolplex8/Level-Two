import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Road extends GameObject {
	public Road(int x, int y, int width, int height, BufferedImage image) {
		super(x, y, width, height, image);
	}

	void update() {

	}

	public void paint(Graphics gra) {
		gra.drawImage(image, x, y, width, height, null);

	}
}
