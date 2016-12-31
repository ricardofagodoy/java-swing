package ricardofagodoy.teste.fase1.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

// Object to map entire json response
public class ItensResponse {
	
	@JsonProperty("data")
	private String date;
	
	private List<Item> itens;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
}