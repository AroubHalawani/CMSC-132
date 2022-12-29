import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.Icon;
import javax.swing.JButton;
//import javax.swing.event.CaretListener;

public class ButtonSquare extends JButton {
	
	public ButtonSquare() {
	
		// Setting the background color to black
		setBackground(Color.BLACK);
		
		// Adding action listener 
		addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent event) {
				
				// Changing the button color depends on the button's current color
				
				if(getBackground()==Color.yellow) {
					
					setBackground(Color.green);
				} else {
					setBackground(Color.red);
				}
			}
		});
		
		
		
		
		
		
	
	}
	
	
}
