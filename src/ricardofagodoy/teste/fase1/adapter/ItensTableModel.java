package ricardofagodoy.teste.fase1.adapter;

import java.awt.Label;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import ricardofagodoy.teste.fase1.controller.ItemController;
import ricardofagodoy.teste.fase1.model.Item;
import ricardofagodoy.teste.fase1.thread.AsyncImageLoader;
import ricardofagodoy.teste.fase1.util.ApplicationProperties;

public class ItensTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yy hh:mm");
	private static final Integer CELL_SIZE = ApplicationProperties.getInstance().getIntegerProperty("window.cell.size");
	private static final String[] COLUMNS = { "Imagem", "Nome", "Descrição", "Selecionado", "Data seleção" };

	private List<Item> itens;
	private ImageIcon[] images;
	private ItemController itemController;

	public ItensTableModel() {

		super();

		this.itemController = new ItemController();

		// List of all itens (json + database)
		this.itens = this.itemController.getMergedItens();

		// Array of images to be lazy-loaded
		images = new ImageIcon[this.itens.size()];

		// Start threads to load images asynchronously
		for (int i = 0; i < this.itens.size(); i++) {
			images[i] = new ImageIcon();
			new AsyncImageLoader(CELL_SIZE, this.itens.get(i).getImageUrl(), images[i], this).start();
		}
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	// Map from Item list
	@Override
	public Object getValueAt(int row, int col) {

		Item i = this.itens.get(row);

		switch (col) {
		case 0:
			return this.images[row];
		case 1:
			return i.getName();
		case 2:
			return i.getDescription();
		case 3:
			return i.getSelected();
		case 4:
			return i.getSelectedDate();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int col) {
		return COLUMNS[col];
	}

	// First column is image and fourth a checkbox
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		switch (columnIndex) {
		case 0:
			return Label.class;
		case 3:
			return Boolean.class;
		}

		return String.class;
	}

	// Only checkbox is editable
	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 3;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {

		super.setValueAt(value, row, col);

		// When checking/unchecking an item
		if (col == 3) {

			Boolean bool = (Boolean) value;
			Item i = this.itens.get(row);

			i.setSelected(bool);

			// Set correct selected date
			if (bool)
				i.setSelectedDate(SDF.format(new Date()));

			// Persist it to database
			this.itemController.saveItem(i);
		}

		// Update table UI
		this.fireTableRowsUpdated(row, row);
	}

	@Override
	public int getRowCount() {
		return this.itens.size();
	}
}
