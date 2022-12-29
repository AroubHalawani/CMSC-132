import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	public MainPanel(Model model, int gridSize) {
		
		// Setting this panel's layout manager to a BorderLayout.
		setLayout(new BorderLayout());
		
		//Adding an instance of CenterPanel to the region BorderLayout.CENTER
		CenterPanel n = new CenterPanel(model, gridSize);
		add(n, BorderLayout.CENTER);
		
		// Adding an instance of BottomPanel to the region BorderLayout.SOUTH
		BottomPanel m = new BottomPanel(model);
		add(m, BorderLayout.SOUTH);
		
	}
}
