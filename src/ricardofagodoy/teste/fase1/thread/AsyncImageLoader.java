package ricardofagodoy.teste.fase1.thread;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import ricardofagodoy.teste.fase1.util.ImageUtils;

// Thread to load images asynchronously
public class AsyncImageLoader extends Thread {
	
	private String url;
	private ImageIcon image;
	private Integer size;
	private AbstractTableModel model;

	public AsyncImageLoader(Integer size, String url, ImageIcon image, AbstractTableModel table) {
		this.size = size;
		this.url = url;
		this.image = image;
		this.model = table;
	}
	
	@Override
	public void run() {
		
		// This may take some time, that's why I use thread
		ImageIcon temp = ImageUtils.urlToImageIcon(url);
		
		// Resizes to fit table cell
		this.image.setImage(temp.getImage().getScaledInstance(this.size, this.size, Image.SCALE_DEFAULT));
		
		// Updates UI
		this.model.fireTableDataChanged();
	}
}
