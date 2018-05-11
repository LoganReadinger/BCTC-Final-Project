package mainpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Player extends JPanel implements ActionListener, KeyListener{
	Graphics g;
	Graphics ga;
	boolean InitializePos = true;
	boolean InitializeHealth = false;

	static int BoundsLeft = 208;
	static int BoundsRight = 788;
	int BoundsTop = 630;
	int BoundsBottom = 794;
	static int moveLeftRight = 0;
	static int PlayerXPosition;
	static int PlayerYPosition;
	int Size = 50;
	int animInc = 0;
	int IntReflect = 0;
	
	
	
	static Rectangle Bounds = new Rectangle();
	//static Ellipse2D.Double attackRadius = new Ellipse2D.Double();
	
	BufferedImage[] playerLeftRight = new BufferedImage[8];
	BufferedImage[] playerDown = new BufferedImage[8]; 
	BufferedImage[] playerUp = new BufferedImage[8]; 
	
	public void paint(Graphics g, int xPos, int yPos) {
		
        super.paintComponent(g);
        
         if (InitializePos == true) {
        	 PlayerXPosition = xPos;
        	 PlayerYPosition = yPos;
        	 InitializePos = false;
        	 Presentation.IntCurrentMovementDirection = Player.move("right");
        	 
        			//Make lots of BufferedImages for each frame to animate plaYER w/ timer
        			playerUp[0] = SpriteRetrieval.getSprite(0, 2, "player.png", Size);
        			playerUp[1] = SpriteRetrieval.getSprite(1, 2, "player.png", Size);
        			playerUp[2] = SpriteRetrieval.getSprite(2, 2, "player.png", Size);
        			playerUp[3] = SpriteRetrieval.getSprite(3, 2, "player.png", Size);
        			playerUp[4] = SpriteRetrieval.getSprite(4, 2, "player.png", Size);
        			playerUp[5] = SpriteRetrieval.getSprite(5, 2, "player.png", Size);
        			playerUp[6] = SpriteRetrieval.getSprite(6, 2, "player.png", Size);
        			playerUp[7] = SpriteRetrieval.getSprite(7, 2, "player.png", Size);

        			//Make lots of BufferedImages for each frame to animate plaYER w/ timer
        			playerDown[0] = SpriteRetrieval.getSprite(0, 3, "player.png", Size);
        			playerDown[1] = SpriteRetrieval.getSprite(1, 3, "player.png", Size);
        			playerDown[2] = SpriteRetrieval.getSprite(2, 3, "player.png", Size);
        			playerDown[3] = SpriteRetrieval.getSprite(3, 3, "player.png", Size);
        			playerDown[4] = SpriteRetrieval.getSprite(4, 3, "player.png", Size);
        			playerDown[5] = SpriteRetrieval.getSprite(5, 3, "player.png", Size);
        			playerDown[6] = SpriteRetrieval.getSprite(6, 3, "player.png", Size);
        			playerDown[7] = SpriteRetrieval.getSprite(7, 3, "player.png", Size);
        			
        			//Make lots of BufferedImages for each frame to animate plaYER w/ timer
        			playerLeftRight[0] = SpriteRetrieval.getSprite(0, 1, "player.png", Size);
        			playerLeftRight[1] = SpriteRetrieval.getSprite(1, 1, "player.png", Size);
        			playerLeftRight[2] = SpriteRetrieval.getSprite(2, 1, "player.png", Size);
        			playerLeftRight[3] = SpriteRetrieval.getSprite(3, 1, "player.png", Size);
        			playerLeftRight[4] = SpriteRetrieval.getSprite(4, 1, "player.png", Size);
        			playerLeftRight[5] = SpriteRetrieval.getSprite(5, 1, "player.png", Size);
        			playerLeftRight[6] = SpriteRetrieval.getSprite(6, 1, "player.png", Size);
        			playerLeftRight[7] = SpriteRetrieval.getSprite(7, 1, "player.png", Size);
         }
         
        
        //Up Animation
        if (Presentation.IntCurrentAnimationDirection == Player.move("up")) {
        g.drawImage(playerUp[animInc], PlayerXPosition-(Size/2), PlayerYPosition, Size, Size, null);
        }
        
        //Down Animation
        if (Presentation.IntCurrentAnimationDirection == Player.move("down")) {
        g.drawImage(playerDown[animInc], PlayerXPosition-(Size/2), PlayerYPosition, Size, Size, null);
        }
        
        //Left/Right Animations
        if (Presentation.IntCurrentAnimationDirection == Player.move("left")) {
        g.drawImage(playerLeftRight[animInc], PlayerXPosition-(Size/2), PlayerYPosition, Size, Size, null);
        }
        
        if (Presentation.IntCurrentAnimationDirection == Player.move("right")) {
        g.drawImage(playerLeftRight[animInc], PlayerXPosition+(Size/2), PlayerYPosition, -Size, Size, null);
        }
        
        //Debugging Bounds
       //g.setColor(Color.GREEN);
       //g.drawRect(Player.Bounds.x, Player.Bounds.y, Player.Bounds.width, Player.Bounds.height);
       //g.setColor(Color.RED);
       //Left Line
       //g.drawLine(BoundsLeft, BoundsTop, BoundsLeft, BoundsBottom); 
       //Right Line
       //g.drawLine(BoundsRight, BoundsTop, BoundsRight, BoundsBottom); 
       //Top Line
       //g.drawLine(BoundsLeft, BoundsTop, BoundsRight, BoundsTop); 
       //Bottom Line
       //g.drawLine(BoundsLeft, BoundsBottom, BoundsRight, BoundsBottom); 
	}
	
	public Player() {
		//Timer used to process Player subs
		Timer timer = new Timer(40, this);
		timer.setInitialDelay(0);
		timer.start(); 
		
	}
	
	
	//Movement processing
	public static int move(String strDir) {
		int dir = 0;
		
		if (strDir == "up") {
			dir = 1;
		}
		if (strDir == "down") {
			dir = 2;
		}
		if (strDir == "left") {
			dir = 3;
		}
		if (strDir == "right") {
			dir = 4;
		}
		return dir;
	}
	
	public static int stop() {
		return 0;
	}
	
	public void update() {
		if (animInc < 6){
		animInc += 1;
		}
		else {
		animInc = 0;
		}
		
		Bounds.height = Size;
		Bounds.width = 	Size-20;	
		Bounds.x = PlayerXPosition - (Player.Bounds.width / 3) ;
		Bounds.y = PlayerYPosition;
		
		
				//Movement and Animation Loop
	        	if (Presentation.IntCurrentMovementDirection == Player.move("up") && Presentation.BlnGameOver == false && Presentation.BlnGameEnd == false) {
	        		PlayerYPosition -= 10; //Player Speed
	        		Bounds.x = Bounds.x - 10; //Player Correlation to left/right Bounds
	        		Bounds.y = Bounds.y - 10; //Player Correlation to up/down Bounds
	        		Presentation.IntCurrentAnimationDirection = 1;
	        	}
	        	if (Presentation.IntCurrentMovementDirection == Player.move("down") && Presentation.BlnGameOver == false && Presentation.BlnGameEnd == false) {
	        		PlayerYPosition += 10; //Player Speed
	        		Bounds.x = Bounds.x - 10; //Player Correlation to left/right Bounds
	        		Bounds.y = Bounds.y + 10;//Player Correlation to up/down Bounds
	        		Presentation.IntCurrentAnimationDirection = 2;
	        	}
	        	if (Presentation.IntCurrentMovementDirection == Player.move("left") && Presentation.BlnGameOver == false && Presentation.BlnGameEnd == false) {
	        		PlayerXPosition -= 10; //Player Speed
	        		IntReflect = 1;
	        		Presentation.IntCurrentAnimationDirection = 3;
	        		Bounds.x = Bounds.x-(Bounds.width/3); //Resetting Bounds for Reflect
	        	}
	        	
	        	if (Presentation.IntCurrentMovementDirection == Player.move("right") && Presentation.BlnGameOver == false && Presentation.BlnGameEnd == false) { 	
	        		PlayerXPosition += 10; //Player Speed
	        		moveLeftRight = 2;
	        		IntReflect = 0;
	        		Presentation.IntCurrentAnimationDirection = 4;
	        	}
	        	

	        	
	        	//Collisions
	        	if (Bounds.x <= BoundsLeft && Presentation.IntCurrentMovementDirection == Player.move("left")) {
	        		Presentation.IntCurrentMovementDirection = Player.move("right");
	        		moveLeftRight = 2;
	        	}
	        	
	        	if (Bounds.x +Player.Bounds.width >= BoundsRight && Presentation.IntCurrentMovementDirection == Player.move("right")) {
	        		Presentation.IntCurrentMovementDirection = Player.move("left");
	        		moveLeftRight = 1;
	        	}
	        	
	        	if (Bounds.y <= BoundsTop  && Presentation.IntCurrentMovementDirection == Player.move("up")) {
	        		PlayerYPosition = BoundsTop;
	        		if (moveLeftRight == 1) {
	        			Presentation.IntCurrentMovementDirection = Player.move("left");
	        		} else {
	        			Presentation.IntCurrentMovementDirection = Player.move("right");
	        		}
	        	}
	        	
	        	if (Bounds.y + Player.Bounds.height >= BoundsBottom && Presentation.IntCurrentMovementDirection == Player.move("down")) {
	        		PlayerYPosition = BoundsBottom - Player.Bounds.height;
	        		if (moveLeftRight == 1) {
	        			Presentation.IntCurrentMovementDirection = Player.move("left");
	        		} else {
	        			Presentation.IntCurrentMovementDirection = Player.move("right");
	        		}
	        	}
	}
	        
	        

	
	        	
	        	
	@Override
	//Animation, Direction and Collisions
	public void actionPerformed(ActionEvent e) {
		update();
        	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (moveLeftRight == 1) {
			Presentation.IntCurrentMovementDirection = Player.move("left");
		} else {
			Presentation.IntCurrentMovementDirection = Player.move("right");
		}
}
	

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	}