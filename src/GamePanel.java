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
import javax.swing.JOptionPane;
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
	BufferedImage i1, i2, i3, i4, i5, i6, i7;
	GameObject road, road2;
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
			i7 = ImageIO.read(this.getClass().getResourceAsStream("guy.png"));
		} catch (Exception ex) {

		}
		road = new Road(0, 0, frameWidth, frameHeight + speed, i1, speed);
		road2 = new Road(0, -frameHeight, frameWidth, frameHeight + speed, i4, speed);
		truck = new Truck(450, 420, 155, 250, i3, speed);
		// original truck dimensions 186, 300
		objects = new ArrayList<GameObject>();
		objects.add(road);
		objects.add(road2);
		objects.add(truck);
		timer = new Timer(1000 / 60, this);
		timer.start();
	}
//creates everything on the game screen 
	public void paintComponent(Graphics g) {
		int newY = road.getY();
		// road.setY(newY += road.getSpeed());

		int newY1 = road2.getY();
		// road2.setY(newY1 += road2.getSpeed());

		if (road.getY() >= frameHeight) {
			road.setY(-frameHeight);
		} else {
			road.setY(newY += road.getSpeed());
		}
		if (road2.getY() >= frameHeight) {
			road2.setY(-frameHeight);
		} else {
			road2.setY(newY1 += road2.getSpeed());
		}

		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.paint(g);
			if (o.getId() == 2) {
				if (truck.getCollisionBox().intersects(o.getCollisionBox()) && ((Enemy) o).alive) {
					objects.remove(o);
					setScore(getScore() - 1);
					System.out.println(getScore());
					new Thread(new SoundPlayer("/Users/league/Desktop/Level-Two/src/squish.wav")).start();
				}
				if (((Enemy) o).alive == false) {
					objects.remove(o);
					// System.out.println("removing");
				}

			}
			if (o.getId() == 3) {
				if (truck.getCollisionBox().intersects(o.getCollisionBox()) && ((Fruit) o).alive) {
					new Thread(new SoundPlayer("/Users/league/Desktop/Level-Two/src/ping.wav")).start();
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
					new Thread(new SoundPlayer("/Users/league/Desktop/Level-Two/src/pine.wav")).start();
					objects.remove(o);
					gamelength += 2;
					System.out.println(getScore());
				}
				if (((Fruit2) o).alive == false) {
					objects.remove(o);
					// System.out.println("removing");
				}

			}
			if (o.getId() == 5) {
				if (truck.getCollisionBox().intersects(o.getCollisionBox()) && ((Guy) o).alive) {
					new Thread(new SoundPlayer("/Users/league/Desktop/Level-Two/src/wilhelm.wav")).start();
					objects.remove(o);
					System.out.println(getScore());
					score -= 5;
				}
				if (((Guy) o).alive == false) {
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
		g.drawString("" + getScore(), 30, 150);
		g.setFont(new Font(Font.SANS_SERIF, 50, 50));
		g.drawString("Score:", 20, 50);
		
		g.setColor(Color.YELLOW);
		g.drawString("Time:", 850, 50);
		g.setFont(new Font(Font.SANS_SERIF, 100, 100));
		g.drawString("" + timeDown / 1000, 850, 150);

		if (difference / 1000 == gamelength) {
			GameWindow.closeGame();
			String name = JOptionPane.showInputDialog("What is your name fearless driver?");
			
		}
	}
//adds the bad fruit if a random number between 0 and 100 equals 1
	void addRotten() {
		if (rand.nextInt(100) == 1) {
			Enemy e = new Enemy(rand1.nextInt(812 - 156) + 156, -(225 / 4), 229 / 4, 225 / 4, i2, speed);
			objects.add(e);
			rand = new Random();
		}
	}
//adds the good fruit if a random number between 0 and 100 equals 1
	void addFruit() {
		if (rand.nextInt(100) == 1) {
			Fruit e = new Fruit(rand1.nextInt(812 - 156) + 156, -(225 / 4), 509 / 8, 518 / 8, i5, speed);
			objects.add(e);
			rand = new Random();
		}
	}
//adds the pineapple if a random number between 0 and 250 equals 1
	void addFruit2() {
		if (rand.nextInt(250) == 1) {
			Fruit2 e = new Fruit2(rand1.nextInt(812 - 156) + 156, -(225 / 4), 640 / 10, 1068 / 10, i6, speed);
			objects.add(e);
			rand = new Random();
		}
	}
//adds the pedestrian if a random number between 1 and 250 equals 1
	void addGuy() {
		if (rand.nextInt(250) == 1) {
			Guy e = new Guy(rand1.nextInt(812 - 156) + 156, -(225 / 4),  73, 70, i7, speed);
			//109, 105
			objects.add(e);
			rand = new Random();
		}
	}
//adds objects to the game panel and does time math 
	public void update() {
		long lEndTime = new Date().getTime();
		difference = lEndTime - lStartTime;
		timeDown = gamelength - difference + gamelength * 1000;
		addRotten();
		addFruit();
		addFruit2();
		addGuy();
		// if (nextSpeedUp == difference / 1000) {
		// speed += 1;
		// road.setSpeed(speed);
		// road2.setSpeed(speed);
		// truck.setSpeed(speed);
		// nextSpeedUp += 10;
		// System.out.println("speeding up, new speed is " + speed);
		// }
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
//allows truck to be moved and honked
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
			new Thread(new SoundPlayer("/Users/league/Desktop/Level-Two/src/honk.wav")).start();
			for (int i = 0; i < objects.size(); i++) {
				GameObject o = objects.get(i);
				if (o.getId() == 5) {
					if (((Truck) truck).guyRect.intersects(o.getCollisionBox()) && ((Guy) o).alive) {
						objects.remove(o);
						score+=1;
					}
				}
			}
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