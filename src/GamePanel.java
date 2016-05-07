import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.naming.CommunicationException;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	ArrayList<GameObject> objects;
	Timer timer;
	int x = 0;
	public int score;
	int speed = 5;
	Random rand = new Random();
	Random rand1 = new Random();
	int difficulty = 100;
	boolean hit = false;
	BufferedImage i1;
	BufferedImage i4;
	BufferedImage i2;
	BufferedImage i3;
	BufferedImage i5;
	BufferedImage i6;
	GameObject road;
	GameObject road2;
	GameObject truck;
	int frameHeight;
	int frameWidth;
	long lStartTime;
	long difference;
	public long gamelength = 20;
	public long timeDown = 10;
	int nextSpeedUp = 10;

	GamePanel() {
		frameHeight = 750;
		frameWidth = 1000;
		lStartTime = new Date().getTime();
		setScore(0);
		try {
			i1 = ImageIO.read(this.getClass().getResourceAsStream("jungle.png"));
			i4 = ImageIO.read(this.getClass().getResourceAsStream("jungle.png"));
			i2 = ImageIO.read(this.getClass().getResourceAsStream("rotten.png"));
			i3 = ImageIO.read(this.getClass().getResourceAsStream("truck.png"));
			i5 = ImageIO.read(this.getClass().getResourceAsStream("realFruit.png"));
			i6 = ImageIO.read(this.getClass().getResourceAsStream("pineapple.png"));
		} catch (Exception ex) {

		}
		road = new Road(0, 0, frameWidth, frameHeight + speed, i1, speed);
		road2 = new Road(0, -frameHeight, frameWidth, frameHeight + speed, i4, speed);
		truck = new Truck(450, 420, 93 * 2, 150 * 2, i3, speed);

		objects = new ArrayList<GameObject>();
		objects.add(road);
		objects.add(road2);
		objects.add(truck);
		timer = new Timer(1000 / 60, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		int newY = road.getY();
		//road.setY(newY += road.getSpeed());

		int newY1 = road2.getY();
		//road2.setY(newY1 += road2.getSpeed());

		if (road.getY() >= frameHeight) {
			road.setY(-frameHeight);
		}
		else{
			road.setY(newY+=road.getSpeed());
		}
		if (road2.getY() >= frameHeight) {
			road2.setY(-frameHeight);
		}
		else{
			road2.setY(newY1+=road2.getSpeed());
		}

		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.paint(g);
			if (o.getId() == 2) {
				if (truck.getCollisionBox().intersects(o.getCollisionBox()) && ((Enemy) o).alive) {
					objects.remove(o);
					setScore(getScore() - 1);
					System.out.println(getScore());
					new Thread(new SoundPlayer("squish.wav")).start();
				}
				if (((Enemy) o).alive == false) {
					objects.remove(o);
					// System.out.println("removing");
				}

			}
			if (o.getId() == 3) {
				if (truck.getCollisionBox().intersects(o.getCollisionBox()) && ((Fruit) o).alive) {
					new Thread(new SoundPlayer("ping.wav")).start();
					objects.remove(o);
					setScore(getScore() + 1);
					System.out.println(getScore());
				}
				if (((Fruit) o).alive == false) {
					objects.remove(o);
					// System.out.println("removing");
				}

			}
			if (o.getId() == 4) {
				if (truck.getCollisionBox().intersects(o.getCollisionBox()) && ((Fruit2) o).alive) {
					new Thread(new SoundPlayer("pine.wav")).start();
					objects.remove(o);
					gamelength += 2;
					System.out.println(getScore());
				}
				if (((Fruit2) o).alive == false) {
					objects.remove(o);
					// System.out.println("removing");
				}

			}
		}
		if (getScore() > 0) {
			g.setColor(Color.GREEN);
		} else if (getScore() == 0) {
			g.setColor(Color.BLACK);
		} else {
			g.setColor(Color.RED);
		}

		g.setFont(new Font(Font.SANS_SERIF, 100, 100));
		g.drawString("" + getScore(), 30, 100);
		g.setColor(Color.YELLOW);
		g.setFont(new Font(Font.SANS_SERIF, 100, 100));
		g.drawString("" + timeDown / 1000, 850, 100);

		if (difference / 1000 == gamelength) {
			GameWindow.closeGame();
		}
	}

	void addRotten() {
		if (rand.nextInt(100) == 1) {
			Enemy e = new Enemy(rand1.nextInt(812 - 156) + 156, -(225 / 4), 229 / 4, 225 / 4, i2, speed);
			objects.add(e);
			rand = new Random();
		}
	}

	void addFruit() {
		if (rand.nextInt(100) == 1) {
			Fruit e = new Fruit(rand1.nextInt(812 - 156) + 156, -(225 / 4), 509 / 8, 518 / 8, i5, speed);
			objects.add(e);
			rand = new Random();
		}
	}

	void addFruit2() {
		if (rand.nextInt(250) == 1) {
			Fruit2 e = new Fruit2(rand1.nextInt(812 - 156) + 156, -(225 / 4), 640 / 10, 1068 / 10, i6, speed);
			objects.add(e);
			rand = new Random();
		}
	}

	public void update() {
		long lEndTime = new Date().getTime();
		difference = lEndTime - lStartTime;
		timeDown = gamelength - difference + gamelength * 1000;
		addRotten();
		addFruit();
		addFruit2();
//		if (nextSpeedUp == difference / 1000) {
//			speed += 1;
//			road.setSpeed(speed);
//			road2.setSpeed(speed);
//			truck.setSpeed(speed);
//			nextSpeedUp += 10;
//			System.out.println("speeding up, new speed is " + speed);
//		}
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			new Thread(new SoundPlayer("honk.wav")).start();
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
