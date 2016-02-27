import javax.swing.JFrame;

public class GameWindow {
	JFrame frame;
	GamePanel panel;

	public static void main(String[] args) {
		GameWindow gw = new GameWindow();
		gw.createUI();
	}

	public void createUI() {
		frame = new JFrame("Thai Fruit Truck!");
		panel = new GamePanel();
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800*2, 600*2);
		
	}

}
