import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.lang.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GameWindow implements KeyListener, ActionListener {
	static JFrame frame;
	static JFrame frame2;
	static GamePanel panel;
	static JPanel panel2;
	JButton instructions = new JButton("Instructions");
	BufferedImage i1;
	ImageIcon img = new ImageIcon(getClass().getResource("inst.png"));
	static String scores = "";
	
	public static void main(String[] args) {
		GameWindow gw = new GameWindow();
		gw.createUI();
	    // The name of the file to open.
        String fileName = "highscores.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                scores = scores + "\n" + line;
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}

	JButton restart = new JButton("Start Game");
	JButton high = new JButton("High Scores");
	static JLabel score = new JLabel();
	
//creates the frame and its settings
	public void createUI() {
		try {
			i1 = ImageIO.read(this.getClass().getResourceAsStream("startPic.jpg"));
		} catch (Exception ex) {

		}
		instructions.setFont(instructions.getFont().deriveFont(20f));
		restart.setFont(restart.getFont().deriveFont(20f));
		high.setFont(high.getFont().deriveFont(20f));
		frame = new JFrame("Thai Fruit Truck!");
		frame.addKeyListener(this);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(1000, 750);
		//frame.setIconImage(img.getImage());
		frame.setResizable(false);

		frame2 = new JFrame("Thai Fruit Truck!");
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		panel2 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(i1, 0, 0, 1026, 778, null);
				g.setFont(new Font(Font.SANS_SERIF, 80, 80));
				if (panel != null) {
					g.drawString("Your score is " + panel.getScore(), 150, 100);
				}
			}
		};

		frame2.setSize(1000, 750);
		restart.addActionListener(this);
		panel2.add(restart);
		frame2.add(panel2);
		panel2.add(instructions);
		panel2.add(high);
		high.addActionListener(this);
		instructions.addActionListener(this);
	}

//closes the game	
	public static void closeGame() {
		frame.setVisible(false);
		frame2.setVisible(true);
		frame.remove(panel);
	}
//restarts the game
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

	//allows button on frame to be used
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton buttonPressed = (JButton) e.getSource();
		if (buttonPressed == restart) {
			restart();
		}
		if (buttonPressed == high) {
			JOptionPane.showMessageDialog(null, scores);
		}
		if(buttonPressed == instructions){
			JOptionPane.showMessageDialog(null, 
					"Get points by collecting good fruit (green) and avioding bad fruit (brown)."
					//+"\n"
					+ "\nPineapples add 2 seconds to your time."
					//+"\n"
					+ "\nUse arrow keys to move."
					//+"\n"
					+ "\nDon't hit the poeple! You can use space to honk people out of the way (+1pt)."
					+ "\nGood Luck and safe driving!", "Instructions", JOptionPane.INFORMATION_MESSAGE, img);
		}
	}

}