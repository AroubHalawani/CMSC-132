import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Model {

	private ArrayList<ButtonSquare> buttons;
	private static Random random = new Random();
	

	public Model(int size, int delay) {

		// Instantiating the arrayList
		buttons= new ArrayList<ButtonSquare>();
		
		// Adding the current number of new ButtonSquares
		for(int index=0; index < size*size; index++) {
			
			buttons.add(new ButtonSquare());
		}
		
		// Making a Timer
		// Setting the delay for the timer
		Timer myTimer = new Timer(delay, new ActionListener() {
			
			int count=0;
			
			// Defining an anonymous inner class with actionPerformed method
			public void actionPerformed(ActionEvent e) {
				
				
				count++;
				// The first time the method is called
				if(count==1) {
					
					// Locating a random square
					int randomNumber1 = random.nextInt(size*size);
					
					// Changing the random square to yellow
					buttons.get(randomNumber1).setBackground(Color.YELLOW);
					
					// Subsequent calls
				} else {
					
					// Checking to see if there is still another yellow square
					for(int index=0; index<buttons.size(); index++) {
						
						// If there is still a yellow square
						if(buttons.get(index).getBackground()==Color.YELLOW) {
							
							// Changing to white
							buttons.get(index).setBackground(Color.WHITE);
						}
					}
					
					// If the game is not over
					if(!gameOver()) {
						
						int randomNumber2;
						
						do {
							// Looking a random black square 
							randomNumber2 = random.nextInt(size*size);
							
							
							
						} while(buttons.get(randomNumber2).getBackground()!=Color.BLACK);
						
						// Changing it to yellow
						buttons.get(randomNumber2).setBackground(Color.YELLOW);
						
					}
					
				}
				
				
			}
			
		});
		
		// Starting my timer
		myTimer.start();
		
	
	}

	/* We did this one for you */
	public ArrayList<ButtonSquare> getSquares() {
		
		return buttons;
	}

	public boolean gameOver() {		
		
		// The method return true if non of the ButtonSquares in the list are black
		// It is false if there is at least one black square in the list
		for(int index=0; index<buttons.size(); index++) {
			
			if(buttons.get(index).getBackground()==Color.BLACK) {
				
				return false;
				
			}
		}
		return true;
	
	}

	public int getScore() {
		
		int score=0;
		
		for(int index=0; index<buttons.size(); index++) {
			
			// gets 5 points for every square that is currently green
			if(buttons.get(index).getBackground()==Color.GREEN) {
				score+=5;
			}
			// loses 1 point for every square that is currently white
			if(buttons.get(index).getBackground()==Color.WHITE) {
				score-=1;
			}
			// loses 10 points for every square that is currently red.
			if(buttons.get(index).getBackground()==Color.RED) {
				score-=10;
			}
		}
		
		return score;
		
	
	}

}

