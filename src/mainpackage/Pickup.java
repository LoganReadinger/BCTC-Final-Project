package mainpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Pickup extends JPanel implements ActionListener{
    //Declare Variables
	Graphics g;
	boolean Initialize = true;
	boolean singleCollide = false;
	boolean visible = true;
	boolean checkVisible = false;
	
	
	int PickupxPosition;
	int PickupyPosition;
	int level;
	int Damage = 1;
	int Health;
	int Size;
	int ColorID;
	int PickupsCleared;
	int UniqueID;
	int PickupRngResult = 0;
	
	
	static Rectangle Bounds = new Rectangle(); //bounds for the sprite
	
	public void paint(Graphics g, int xPos, int yPos, int levelEnemy, int type) {
		
		//Show Player Bounds
		//g.setColor(Color.orange);
        //g.drawRect(this.PickupxPosition - 29, this.PickupyPosition + 3, Bounds.width ,Bounds.height );
        
        super.paintComponent(g); //all painting for graphics 2D
        
		//Initialize Variables//
         if (Initialize == true) {
        	 //Pickup Box Set to its own bounds
        	 PickupxPosition = xPos + 203;
        	 PickupyPosition = yPos + 630;
        	 Initialize = false;
        	 Size = 32;
         }
         //Pickup Colors
         if (type == 1) {
    		 ColorID = 1;
    	 } else if (type == 2) {
    		 ColorID = 2;
    	 }  
    	 
         //Show Pickups if visible equal true
		 //Good
    	 if(visible == true && ColorID == 0) {
    	 g.drawImage(SpriteRetrieval.getSprite(0, 0, "pickup.png", 32), PickupxPosition, PickupyPosition, -Size, Size, null);
    	 }
    	 //Bad
    	 if(visible == true && ColorID == 1 && Presentation.IntPickupsCleared != 4) {
    	 g.drawImage(SpriteRetrieval.getSprite(1, 0, "pickup.png", 32), PickupxPosition, PickupyPosition, -Size, Size, null);
    	 }
    	 //Making a new wave if 4 good are cleared
    	 if(Presentation.IntPickupsCleared == 4) {
    		 Presentation.IntPickupsCleared = 0;
    		 visible = false;
    		 waveNum(1, 5);
    		 //System.out.println(Presentation.IntWaveNumber);
    		 
    	 }
    	
	}
	
	public Pickup() {
		//Timer used to process Pickup subs
		Timer timer = new Timer(20, this);
		timer.setInitialDelay(0);
		timer.start(); 
		
	}

	
	//Pickup Cycling from Left to Right
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.PickupxPosition -= 18;
		if (this.PickupxPosition <= Player.BoundsLeft) {
			this.PickupxPosition = Player.BoundsRight + 32;
		}

		Bounds.height = Size - 8;
		Bounds.width = 	Size - 8;	
		Bounds.x = this.PickupxPosition - 29;
		Bounds.y = this.PickupyPosition + 3;
		
		//Pickup Collisions
    	if (Player.Bounds.intersects(Pickup.Bounds)) {
    		if (this.ColorID == 1 && singleCollide == false && visible == true) {
    			Presentation.healthDecrease(Damage);
    			this.singleCollide = true;
    			this.visible = false;
    			//System.out.println(this.UniqueID + " - Damage Self");
    			
    			
    		} else if (this.ColorID == 0 && singleCollide == false && visible == true) {
    			Presentation.healthDecreaseEnemy(Damage);
    			this.singleCollide = true;
    			this.visible = false;
    			Presentation.IntPickupsCleared += 1;
    			PickupsCleared = Presentation.IntPickupsCleared;
    		}
    	} else {
    		singleCollide = false;
    	}
    	
	}
	
	public void setNewPos(int x, int y) {
		this.PickupxPosition = x + 203;
		this.PickupyPosition = y + 630;
		this.visible = true;
	}
	
	public void waveNum(int min, int max) {
	    Random rand = new Random();
	    
	    Presentation.IntWaveNumber = rand.nextInt((max - min) + 1) + min;
	}
}