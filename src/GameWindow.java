import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//Add people who move at honk, but -10pts if you hit them
public class GameWindow implements KeyListener, ActionListener {
	static JFrame frame;
	static JFrame frame2;
	static GamePanel panel;
	static JPanel panel2;
	JButton instructions = new JButton("Instructions");
	BufferedImage i1;

	public static void main(String[] args) {
		GameWindow gw = new GameWindow();
		gw.createUI();
	}

	JButton restart = new JButton("Start Game");
	static JLabel score = new JLabel();

	public void createUI() {
		try {
			i1 = ImageIO.read(this.getClass().getResourceAsStream("startPic.jpg"));
		} catch (Exception ex) {

		}
		frame = new JFrame("Thai Fruit Truck!");
		frame.addKeyListener(this);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(1000, 750);
		frame.setResizable(false);

		frame2 = new JFrame("Thai Fruit Truck!");
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		panel2 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(i1, 0, 0, 1026, 778, null);
				g.setFont(new Font(Font.SANS_SERIF, 100, 100));
				if (panel != null) {
					g.drawString("Your score is " + panel.getScore(), 100, 100);
				}
			}
		};

		frame2.setSize(1000, 750);
		restart.addActionListener(this);
		panel2.add(restart);
		frame2.add(panel2);
		panel2.add(instructions);
		instructions.addActionListener(this);
	}

	public static void closeGame() {
		frame.setVisible(false);
		frame2.setVisible(true);
		frame.remove(panel);
	}

	public void restart() {
		panel = new GamePanel();
		frame.add(panel);
		frame.setVisible(true);
		frame2.setVisible(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		panel.keyPressed(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		panel.keyReleased(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton buttonPressed = (JButton) e.getSource();
		if (buttonPressed == restart) {
			restart();
		}
		if(buttonPressed == instructions){
			JOptionPane.showMessageDialog(null, "Get Points. Arrow keys to move. Space to honk. Good Luck and safe driving!");
		}
	}

}
