package thread;

import java.util.ArrayList;

import application.Controller;
import model.Game;
import model.Pacman;

public class PacmanThread extends Thread{
	
	private Controller cv;
	private Game game;
	
	public PacmanThread(Controller cv, Game game) {
		
		this.cv = cv;	
		this.game = game;
	}

	
	public void run() {
		
		while(true) {
			
			ArrayList<Pacman> pacmans = game.getPacmans();
			
			
			for(int i = 0; i< pacmans.size(); i++) {
				
				Pacman pac = pacmans.get(i);

				pac.movePacman();
//				if(game.validateCollition()) { 
//					
//					System.out.println("I enter here");
//				}
				game.validateCollition();
				cv.moverF(pac.getPosX(), pac.getPosY(), i);
				
				
			}
			
			try {
				
				sleep(50);
				
			}catch(InterruptedException e) {

				
			}
		}
	}
}
