
package mainpackage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.JLabel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Menu extends JFrame implements KeyListener, MouseListener {
	 public JFrame frame = new JFrame();
	 private 
	//Housing for JLabel
    JPanel contentPane;
    //Housing for Image Icon
    JLabel gifLabel = new JLabel();
    JLabel titleLabel = new JLabel();
    JLabel menuLabel = new JLabel();
    int intMenuChoice = 1;
    
    Point[] buttonBounds = {
    		//TopLeft to BottomRight
    		new Point(299, 323),
    		new Point(351, 341),
    		
    		new Point(296, 352),
    		new Point(354, 370),
    		
    		new Point(301, 378),
    		new Point(348, 397),
    		
    };
    
    ImageIcon menuChoice_1 = new ImageIcon(loader.getResource("select_1.png"));
    ImageIcon menuChoice_2 = new ImageIcon(loader.getResource("select_2.png"));
    ImageIcon menuChoice_3 = new ImageIcon(loader.getResource("select_3.png"));
    ImageIcon title = new ImageIcon(loader.getResource("menu_title.png"));
    ImageIcon mainGif = new ImageIcon(loader.getResource("menu_bg.gif"));
    
    //Allows loading from relative path (Added to build path props)
    static final ClassLoader loader = Menu.class.getClassLoader();

    public Menu() {
        try {
        	
        	//Window Creation
            //frame.setPreferredSize(new Dimension(676, 500));
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            contentPane = (JPanel) frame.getContentPane();
            
            //contentPane.setLayout(new OverlayLayout(contentPane));
            frame.setSize(new Dimension(676, 500));
            frame.setTitle("Main Menu");
            
            //frame.setResizable(false);
            frame.addKeyListener(this);
            frame.addMouseListener(this);
            
            // add the JLabel and Image Icon to JLabel
            menuLabel.setIcon(menuChoice_1);
            menuLabel.setBounds(280, 280, 86, 102);
            
            
            titleLabel.setIcon(title);
        	titleLabel.setBounds(95, 40, 496, 208);
        	
            
            gifLabel.setIcon(mainGif);
            gifLabel.setBounds(0, 0, 676, 766);
            
            contentPane.add(menuLabel);
            contentPane.add(titleLabel);
            contentPane.add(gifLabel);
            

            // Make Window Visible
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    


    public static void main(String[] args) {
        new Menu();
    }

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}



	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 38) {
			
			intMenuChoice -= 1;
			if (intMenuChoice == 0) {
				intMenuChoice = 3;
			}
		}
		
		if (e.getKeyCode() == 40) {
			intMenuChoice += 1;
			if (intMenuChoice == 4) {
				intMenuChoice = 1;
			}
		}
		if (intMenuChoice == 1) {
		menuLabel.setIcon(menuChoice_1);
		}
		if (intMenuChoice == 2) {
		menuLabel.setIcon(menuChoice_2);
		}
		if (intMenuChoice == 3) {
		menuLabel.setIcon(menuChoice_3);
		}
		
		if (e.getKeyCode() == 90) {
			if (intMenuChoice == 1) {
				new GameDisplayWindow();
				}
			if (intMenuChoice == 3) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		}
		if (e.getKeyCode() == 27) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
		
		}



	@Override
	public void mouseClicked(MouseEvent e) {
		Point mousePoint = e.getPoint();
		
		if(mousePoint.x >= buttonBounds[0].x & mousePoint.y >= buttonBounds[0].y) {
			if(mousePoint.x <= buttonBounds[1].x & mousePoint.y <= buttonBounds[1].y) {
				new GameDisplayWindow();
				System.out.println("Play");
				menuLabel.setIcon(menuChoice_1);
				
			}
		}
		
		if(mousePoint.x >= buttonBounds[2].x & mousePoint.y >= buttonBounds[2].y) {
			if(mousePoint.x <= buttonBounds[3].x & mousePoint.y <= buttonBounds[3].y) {
				System.out.println("Load");
				menuLabel.setIcon(menuChoice_2);
			}
		}
		
		if(mousePoint.x >= buttonBounds[4].x & mousePoint.y >= buttonBounds[4].y) {
			if(mousePoint.x <= buttonBounds[5].x & mousePoint.y <= buttonBounds[5].y) {
				System.out.println("Exit");
				menuLabel.setIcon(menuChoice_3);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		}
	}



	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	}

