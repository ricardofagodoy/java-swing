package ricardofagodoy.teste.fase1.view;

import javax.swing.JTable;
import ricardofagodoy.teste.fase1.adapter.ItensTableModel;
import ricardofagodoy.teste.fase1.adapter.TableImageRenderer;
import ricardofagodoy.teste.fase1.adapter.TableTextAreaRenderer;
import ricardofagodoy.teste.fase1.util.ApplicationProperties;

public class ItemsList extends JTable {

	private static final long serialVersionUID = 1L;
	private static final Integer CELL_SIZE = ApplicationProperties.getInstance().getIntegerProperty("window.cell.size");
	
	public ItemsList() {
		
		// Actual JTable with custom data model
		super(new ItensTableModel());
		
		// Some row/column size and renderer adjusts
		this.getColumnModel().getColumn(0).setCellRenderer(new TableImageRenderer());
		this.setDefaultRenderer(String.class, new TableTextAreaRenderer());
		
		this.setRowHeight(CELL_SIZE);
		this.setCellSelectionEnabled(false);
	}
}
