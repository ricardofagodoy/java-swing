package ricardofagodoy.teste.fase1.adapter;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableImageRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object img, boolean isSelected,boolean hasFocus, int row, int column) {

		JLabel label = new JLabel();
	 
		// Renders an ImageIcon instead of text
	    if (img != null && img instanceof ImageIcon)
	    		label.setIcon((ImageIcon) img);
	 
	    return label;
	}
}
