package ricardofagodoy.teste.fase1.adapter;

import java.awt.Component;
import java.awt.Insets;
import java.io.UnsupportedEncodingException;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

public class TableTextAreaRenderer extends DefaultTableCellRenderer {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object text, boolean isSelected,boolean hasFocus, int row, int column) {

		JTextArea area = new JTextArea();
		
		String fixed = "";
		byte[] bytes;
		
		// In order to fix bad characters coming from URL json
		if (text != null)
			try {
				bytes = ((String)text).getBytes("latin1");
				fixed = new String(bytes, "UTF8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		
		area.setText(fixed);	 
		
		// Better visualization of data
		area.setMargin(new Insets(20, 5, 5, 0));
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
	 
	    return area;
	}
}
