package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import model.Game;
import model.Pacman;
import thread.PacmanThread;

public class Controller {
	
	private Game game;
	 @FXML
	 private Label rebound;

	@FXML
	private AnchorPane pane;
	private ArrayList<Arc> arcs;
	
	 @FXML
	   void mouse(MouseEvent event) {

	    }
	@FXML
	public void eMoverFig(ActionEvent e) {
		
		PacmanThread hf= new PacmanThread(this, game);
		hf.start();
		
	}
	
	public void moverF(int posX, int posY, int i) {
		arcs.get(i).setLayoutX(posX);
		arcs.get(i).setLayoutY(posY);
		
	}
	
	@FXML
	public void initialize() {

		game = new Game(0);
		
		ArrayList<Pacman> pacs = game.getPacmans();
		
		arcs = new ArrayList<Arc>();
		
		for(int i = 0; i< pacs.size(); i ++) {
			
			Arc pacArc = new Arc(pacs.get(i).getPosX(), pacs.get(i).getPosY(), pacs.get(i).getDiameter()/2, pacs.get(i).getDiameter()/2,45,270);
			 
			pacArc.setFill(Color.YELLOW);
			pacArc.setType(ArcType.ROUND);
			
			 EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
		         @Override 
		         public void handle(MouseEvent e) { 
		            System.out.println("me dieron clic" + e.getX() +" "+ e.getY()); 
		            pane.getChildren().remove(pacArc);
		       
		         } 
		      }; 
		    pacArc.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		    rebound.setText(""+pacs.get(i).getRebound());
			pane.getChildren().add(pacArc);
			arcs.add(pacArc);
		
		}
		
		
		 
	}
	
	
	   @FXML
	    void loadGame(ActionEvent event) {
		   try {
				ObjectInputStream object = new ObjectInputStream(new FileInputStream(new File("./data/gameInf.pac")));
				Game a = (Game) object.readObject();
				game = a;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void saveGame(ActionEvent event) {
	    	
	    	try {
				ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream("./data/gameInf.pac"));
				
				oos.writeObject(game);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    

	public ArrayList<Arc> getArcs() {
		return arcs;
	}

	public void setArcs(ArrayList<Arc> arcs) {
		this.arcs = arcs;
	}
	
}
