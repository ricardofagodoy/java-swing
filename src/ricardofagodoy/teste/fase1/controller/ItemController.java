package ricardofagodoy.teste.fase1.controller;

import java.util.ArrayList;
import java.util.List;
import ricardofagodoy.teste.fase1.model.Item;
import ricardofagodoy.teste.fase1.model.ItensResponse;
import ricardofagodoy.teste.fase1.repository.ItemRepository;
import ricardofagodoy.teste.fase1.util.ApplicationProperties;
import ricardofagodoy.teste.fase1.util.Heapsort;
import ricardofagodoy.teste.fase1.util.HttpUtils;

public class ItemController {
	
	private ItemRepository repository;
	
	public ItemController() {
		repository = new ItemRepository();
	}
	
	public void saveItem(Item i) {
		this.repository.updateItem(i);
	}
	
	public List<Item> loadItensDatabase() {
		
		List<Item> response = null;
		
		response = repository.getAll();
		
		int rows = response == null ? 0 : response.size();
		System.out.println("Loaded " +  rows + " itens from database");
		
		return response;		
	}
	
	public List<Item> loadItensUrl() {
		
		List<Item> response = null;
		ItensResponse itens = HttpUtils.getJsonList(ApplicationProperties.getInstance().getProperty("itens.url"), "itens", ItensResponse.class);
		
		if (itens != null && itens.getItens() != null) {
			response = itens.getItens();
			System.out.println("Loaded " + response.size() + " itens from url");
		}
		
		return response;
	}

	// Merge both itens from web and database
	public List<Item> getMergedItens() {
		
		List<Item> itensUrl = this.loadItensUrl();
		List<Item> itensDb = this.loadItensDatabase();
		
		// If none came from url, use database
		if (itensUrl == null)
			return itensDb;
		
		if (itensDb == null)
			itensDb = new ArrayList<Item>();
		
		// Add all new itens from url to database
		for(Item i : itensUrl)
			if(itensDb.indexOf(i) < 0) {
				itensDb.add(i);
				repository.insertItem(i);
			}
		
		// Sort names using heapsort algorithm
		new Heapsort<Item>(itensDb).sort();
		
		return itensDb;
	}
}
