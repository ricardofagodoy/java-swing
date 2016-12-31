package ricardofagodoy.teste.fase1.view;

import java.awt.BorderLayout;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class ApplicationFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public ApplicationFrame(String title, Integer width, Integer height) {
		
		super(title);
		
		// Preferred size
		this.setSize(width, height);
		
		// Some borders adjustments on Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 10, 15));
	      
	    BorderLayout layout = new BorderLayout();
	    layout.setHgap(10);
	    layout.setVgap(30);
	    mainPanel.setLayout(layout);
	    
	    // Upper title label
	    mainPanel.add(new JLabel(title, JLabel.CENTER), BorderLayout.NORTH);
	    
	    // Item table and everything
	    mainPanel.add(new JScrollPane(new ItemsList()), BorderLayout.CENTER);
	    
	    // Date time footer
	    mainPanel.add(new JLabel(new Date().toString(), JLabel.CENTER), BorderLayout.SOUTH);
	    
	    mainPanel.setVisible(true);
	    
	    this.add(mainPanel);
	    
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
