import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	ArrayList<GameObject> objects;
	Timer timer;
	int x = 0;
	int score = 0;
	int speed = 5;
	Random rand = new Random();
	Random rand1 = new Random();
	int difficulty = 100;
	boolean hit = false;
	BufferedImage i1;
	BufferedImage i4;
	BufferedImage i2;
	BufferedImage i3;
	GameObject road;
	GameObject road2;
	// GameObject rotten;
	GameObject truck;
	int frameHeight;
	int frameWidth;

	GamePanel() {
		frameHeight = 750;
		frameWidth = 1000;

		try {
			i1 = ImageIO.read(this.getClass().getResourceAsStream("jungle.png"));
			i4 = ImageIO.read(this.getClass().getResourceAsStream("jungle.png"));
			i2 = ImageIO.read(this.getClass().getResourceAsStream("rotten.png"));
			i3 = ImageIO.read(this.getClass().getResourceAsStream("truck.png"));
		} catch (Exception ex) {

		}
		road = new Road(0, 0, frameWidth, frameHeight+speed, i1, speed);
		road2 = new Road(0, -frameHeight, frameWidth, frameHeight+speed, i4, speed);
		// rotten = new Enemy(200, 10, 229 / 4, 225 / 4, i2);
		truck = new Truck(450, 430, 93 * 2, 150 * 2, i3, 4);

		objects = new ArrayList<GameObject>();
		// for (int i = 0; i < 5; i++) {
		// objects.add(new Enemy(100, 100, 229 / 4, 225 / 4, i2));
		// }
		objects.add(road);
		objects.add(road2);
		// objects.add(rotten);
		objects.add(truck);
		timer = new Timer(1000 / 60, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		// g.drawString("" + score, 10, 10);

		// if (rotten.getY() >= frameHeight + 225 / 4) {
		// objects.remove(rotten);
		// }

		int newY = road.getY();
		road.setY(newY += road.getSpeed());
		
		int newY1 = road2.getY();
		road2.setY(newY1 += road2.getSpeed());
		if (road.getY() >= frameHeight) {
			road.setY(-frameHeight);
		} if (road2.getY() >= frameHeight) {
			road2.setY(-frameHeight);
		}

		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.paint(g);
			if (o.getId() == 2) {
				if (((Enemy) o).alive == false) {
					objects.remove(o);
					System.out.println("removing");
				}
				if (truck.getCollisionBox().intersects(road.getCollisionBox()) && ((Enemy) o).alive ){
					objects.remove(o);
					score -= 1;
					System.out.println(score);
				}
			}
		}
	}

	void addFruit() {
		if (rand.nextInt(100) == 1) {
			Enemy e = new Enemy(rand1.nextInt(812 - 156) + 156, -(225 / 4), 229 / 4, 225 / 4, i2, speed);
			objects.add(e);
			rand = new Random();
		}
	}

	public void update() {
		addFruit();
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.update();
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
			((Truck) truck).left = true;
			((Truck) truck).right = false;

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			((Truck) truck).left = false;
			((Truck) truck).right = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			((Truck) truck).left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			((Truck) truck).right = false;
		}
	}

}
