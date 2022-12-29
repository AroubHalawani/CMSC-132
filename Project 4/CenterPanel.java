import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

public class CenterPanel extends JPanel {
	
	public CenterPanel(Model model, int size) {
	
		
		// Set the layout manager of this panel to a GridLayout,
		// Using the parameter "size" as both the width and height of the grid.
		GridLayout n= new GridLayout(size, size);
		
		this.setLayout(n);
		model.getSquares();
		
		for(int m=0; m<model.getSquares().size(); m++) {
			
			this.add(model.getSquares().get(m));
		}
		
	}	
}
