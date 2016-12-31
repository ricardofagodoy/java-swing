package ricardofagodoy.teste.fase1.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item implements Comparable<Item>, Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("foto")
	private String imageUrl;
	
	@JsonProperty("nome")
	private String name;
	
	@JsonProperty("descricao")
	private String description;
	
	private Boolean selected;
	
	private String selectedDate;
	
	public Item() {
		this("", "", "");
	}
	
	public Item(String imageUrl, String name, String description) {
		this(imageUrl, name, description, false, "");
	}
	
	public Item(String imageUrl, String name, String description, Boolean selected, String selectedDate) {
		this.imageUrl = imageUrl;
		this.name = name;
		this.description = description;
		this.selected = selected;
		this.selectedDate = selectedDate;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}
	
	// Only name matters to compare
	@Override
	public boolean equals(Object o) {
		
		if (o == this)
			return true;
		
		if (o instanceof Item && 
				((Item)o).getName().equals(this.getName()))
			return true;
		
		return false;
	}
	
	// Used by heapsort implementation
	@Override
	public int compareTo(Item i) {
		
		if (i == null)
			return -1;
		
		return this.getName().compareTo(i.getName());
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
