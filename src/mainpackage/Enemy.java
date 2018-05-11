package mainpackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Enemy extends JPanel implements ActionListener{
	Graphics g;
	boolean Initialize = true;

	int EnemyxPosition;
	int EnemyyPosition;
	int level;
	
	int Damage;
	int Health;
	int Size;
	int ColorID;
	
	String Color;
	//static String Name;
	
	static Rectangle Bounds = new Rectangle();
	
	
	public static void main(String[] args) {
	}
	
	public void paint(Graphics g, String pic, int levelEnemy, String enemyColor) {
		
        super.paintComponent(g);
        
         if (Initialize == true) {
        	 EnemyxPosition = 800;
        	 EnemyyPosition = 25;
        	 Initialize = false;
        	 Health = levelEnemy * 100;
        	 Damage = levelEnemy * 10;
        	 Size = 600;
        	 Color = enemyColor;
        	 

         }
         
    	 if (Presentation.stringPassColor == "Red") {
    		 ColorID = 0;
    	 }
    	 if (Presentation.stringPassColor == "Orange") {
    		 ColorID = 1;
    	 }
    	 if (Presentation.stringPassColor == "Yellow") {
    		 ColorID = 2;
    	 }
    	 if (Presentation.stringPassColor == "Green") {
    		 ColorID = 3;
    	 }
    	 if (Presentation.stringPassColor == "Blue") {
    		 ColorID = 4;
    	 }
    	 if (Presentation.stringPassColor == "Gray") {
    		 ColorID = 5;
    	 }

         
        g.drawImage(SpriteRetrieval.getSprite(ColorID, 0, pic, Size), EnemyxPosition - Size, EnemyyPosition, Size, Size, null);
        
	}
	
	public Enemy() {
		//Timer used to process Player subs
		//Timer timer = new Timer(80, this);
		//timer.setInitialDelay(0);
		//timer.start(); 
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

}