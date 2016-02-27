import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	ArrayList<GameObject> objects;
	Timer timer;
	int x = 0;
	BufferedImage i1;
	BufferedImage i2;
	BufferedImage i3;
	GameObject road;
	GameObject rotten;
	GameObject truck;

	GamePanel() {
		try {
			i1 = ImageIO.read(this.getClass().getResourceAsStream("jungle.jpg"));
			i2 = ImageIO.read(this.getClass().getResourceAsStream("rotten.png"));
			i2 = ImageIO.read(this.getClass().getResourceAsStream("truck.png"));
		} catch (Exception ex) {

		}
		road = new Road(0, 0, 800 * 2, 600 * 2, i1);
		truck = new Truck(500, 10, 93*4, 150*4, i3);
		objects = new ArrayList<GameObject>();
		for (int i = 0; i < 5; i++) {
			objects.add(new Enemy(10, 10, 229 / 4, 225 / 4, i2));
		}
		objects.add(road);
		objects.add(rotten);
		timer = new Timer(1000 / 60, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		for (GameObject go : objects) {
			go.paint(g);
		}
	}
	public void update(){
		for (GameObject go : objects) {
			go.update();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
	}
}
