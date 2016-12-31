package ricardofagodoy.teste.fase1.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;
import ricardofagodoy.teste.fase1.model.Item;
import ricardofagodoy.teste.fase1.util.ApplicationProperties;

// My class for database operations
public class ItemRepository {
	
	private static final ApplicationProperties properties = ApplicationProperties.getInstance();
	private Connection conn;
	
	public ItemRepository() {
		
		// Connect to database local
		try {
			Class.forName("org.sqlite.JDBC");
			this.conn = DriverManager.getConnection("jdbc:sqlite:"+ properties.getProperty("db.name"));
			
			// Create table if not exists
			Statement stmt = this.conn.createStatement();
			stmt.executeUpdate(properties.getProperty("db.item.create"));
			stmt.close();			
			
			System.out.println("Opened database successfully");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Get all Item stored in local database, if any
	public List<Item> getAll() {
		List<Item> result = null;
		
		Statement stmt;
		try {
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(properties.getProperty("db.item.select"));
			
			if (rs.isBeforeFirst()) {
				
				System.out.println("Fetched registers from database");
				
				result = new ArrayList<Item>();
				
				while (rs.next()) {
					result.add(new Item(rs.getString("image"), 
									    rs.getString("name"),
									    rs.getString("description"),
									    BooleanUtils.toBoolean(rs.getInt("selected")),
									    rs.getString("date")));
					
				}
			}
			
		    rs.close();
		    stmt.close();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// Simply updates item selected and selectedDate by its name
	public void updateItem(Item i) {
		
		if (i == null)
			return;
		
		PreparedStatement prep;
		
		try {
			prep = conn.prepareStatement(properties.getProperty("db.item.update"));
			
			prep.setInt(1, i.getSelected() ? 1:0);
		    prep.setString(2, i.getSelectedDate());
		    prep.setString(3, i.getName());
		    prep.addBatch();
		    
		    prep.executeBatch();
		    
		    System.out.println(i + " updated!");
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Insert new item came from json to database
	public void insertItem(Item i) {
		
		if (i == null)
			return;
		
		PreparedStatement prep;
		
		try {
			prep = conn.prepareStatement(properties.getProperty("db.item.insert"));
			
			prep.setString(1, i.getName());
		    prep.setString(2, i.getDescription());
		    prep.setString(3, i.getImageUrl());
		    prep.setInt(4, i.getSelected() ? 1:0);
		    prep.setString(5, i.getSelectedDate());
		    
		    prep.addBatch();
		    prep.executeBatch();
		    
		    System.out.println(i + " saved!");
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
