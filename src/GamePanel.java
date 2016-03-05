import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	ArrayList<GameObject> objects;
	Timer timer;
	int x = 0;
	BufferedImage i1;
	BufferedImage i4;
	BufferedImage i2;
	BufferedImage i3;
	GameObject road;
	GameObject road2;
	GameObject rotten;
	GameObject truck;


	GamePanel() {
		try {
			i1 = ImageIO.read(this.getClass().getResourceAsStream("jungle.jpg"));
			i4 = ImageIO.read(this.getClass().getResourceAsStream("jungle.jpg"));
			i2 = ImageIO.read(this.getClass().getResourceAsStream("rotten.png"));
			i3 = ImageIO.read(this.getClass().getResourceAsStream("truck.png"));
		} catch (Exception ex) {

		}
		road = new Road(0, 0, 800 * 2, 600 * 2, i1);
		road2 = new Road(0, -600 * 2, 800 * 2, 600 * 2, i4);
		rotten = new Enemy(10, 10, 229 / 4, 225 / 4, i2);
		truck = new Truck(650, 550, 93 * 2, 150 * 2, i3);

		objects = new ArrayList<GameObject>();
		// for (int i = 0; i < 5; i++) {
		// objects.add(new Enemy(100, 100, 229 / 4, 225 / 4, i2));
		// }
		objects.add(road);
		objects.add(road2);
		objects.add(rotten);
		objects.add(truck);
		timer = new Timer(1000 / 60, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		int newY = road.getY();
		road.setY(newY += 2);
		int newY1 = road2.getY();
		road2.setY(newY1 += 2);
		if (road.getY() == 600 * 2) {
			road.setY(-600 * 2);
		} else if (road2.getY() == 600 * 2) {
			road2.setY(-600 * 2);
		}

		for (GameObject go : objects) {
			go.paint(g);
		}
	}

	public void update() {
		for (GameObject go : objects) {
			go.update();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		((Truck)truck).left=true;
		((Truck)truck).right=false;

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			((Truck)truck).left=false;
			((Truck)truck).right=true;
			//int newX = truck.getX();
			//truck.setX(newX +=5);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
