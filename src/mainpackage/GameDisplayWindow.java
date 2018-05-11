package mainpackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameDisplayWindow extends JFrame implements KeyListener, ActionListener{
	Dimension size = new Dimension(1000, 825);
	static JFrame frame = new JFrame("Game Window");
	static JFrame battleFrame = new JFrame();
	public static void main(String[] args) {
		new GameDisplayWindow();

	}
	//Declare JFrame to house JPanel
	public GameDisplayWindow() {
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size);
        frame.setTitle("Astro Arena");
        frame.setResizable(false);
        frame.add(new Presentation());
        // Make Window Visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

	}

	@Override
	public void keyPressed(KeyEvent ke) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}
