import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BottomPanel extends JPanel {

	// Two instance variables
	private JLabel score = new JLabel("Score: 0");
	private static Font font = new Font("Arial", Font.BOLD, 30);
	
	public BottomPanel(Model model) {
		
		// Setting the font of the JLabel to the instance variable "font".
		score.setFont(font);
		
		// Adding the JLabel to this panel.
		this.add(score);
		
		// Creating a Timer that will update the score periodically.
		// Setting the delay of the timer to 500.
		Timer myTimer = new Timer(500, new ActionListener() {
			
			// The actionPerformed method should get the current score from the model
			public void actionPerformed(ActionEvent e) {
				
				// Setting the text of the JLabel to the String "Score: " followed by the current score.
				score.setText("Score "+ model.getScore()); 
				
			}
			
			
			
		});
		
		// Starting timer
		myTimer.start();
	
	}
}
