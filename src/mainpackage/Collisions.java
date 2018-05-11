package mainpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Collisions implements ActionListener {
	static boolean allowmove = true;
	public static void main(String[] args) {
	}
	public Collisions() {
		//Timer used to process Player subs
		Timer timer = new Timer(80, this);
		timer.setInitialDelay(0);
		timer.start(); 
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
    	if (Player.Bounds.intersectsLine(Enemy.Bounds.x, Enemy.Bounds.y, Enemy.Bounds.x + Enemy.Bounds.width, Enemy.Bounds.y) && Presentation.IntCurrentMovementDirection == Player.move("down")) {
    		Player.PlayerYPosition = (Enemy.Bounds.y - Player.Bounds.height);
    		Presentation.healthDecrease(1);
    		allowmove = false;
    		
    	} else if (Player.Bounds.intersectsLine(Enemy.Bounds.x, Enemy.Bounds.y + Enemy.Bounds.height, Enemy.Bounds.x + Enemy.Bounds.width, Enemy.Bounds.y + Enemy.Bounds.height) && Presentation.IntCurrentMovementDirection == Player.move("up")) {
    		Player.PlayerYPosition = (Enemy.Bounds.y + Enemy.Bounds.height);
    		Presentation.healthDecrease(1);
    		allowmove = false;
    		
    	} else if (Player.Bounds.intersectsLine(Enemy.Bounds.x, Enemy.Bounds.y, Enemy.Bounds.x, Enemy.Bounds.y + Enemy.Bounds.height) && Presentation.IntCurrentMovementDirection == Player.move("right")) {
    		Player.PlayerXPosition = (Enemy.Bounds.x - (Player.Bounds.width / 2));
    		Presentation.healthDecrease(1);
    		allowmove = false;
    		
    	} else if (Player.Bounds.intersectsLine(Enemy.Bounds.x + Enemy.Bounds.width, Enemy.Bounds.y, Enemy.Bounds.x + Enemy.Bounds.width, Enemy.Bounds.y + Enemy.Bounds.height) && Presentation.IntCurrentMovementDirection == Player.move("left")) {
    		Player.PlayerXPosition = ((Enemy.Bounds.x + Enemy.Bounds.width) + (Player.Bounds.width / 2));
    		Presentation.healthDecrease(1);
    		allowmove = false;
    		
    	} else {
    		allowmove = true;
		}
	}
}
