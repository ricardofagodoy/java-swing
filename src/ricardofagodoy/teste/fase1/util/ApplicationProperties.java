package ricardofagodoy.teste.fase1.util;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {
	
	private final Properties properties;
	
	// Singleton properties
	private static ApplicationProperties instance;
	
	public static ApplicationProperties getInstance() {
		if (instance == null)
			instance = new ApplicationProperties();
		
		return instance;
	}
	
	private ApplicationProperties() {
		
		System.out.println("Loading properties...");
		
		properties = new Properties();
		
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
			
		} catch (IOException e) {
			System.err.println(String.format("%s: %s", e.getMessage(), 
					"Aplicação não pode iniciar sem as configurações."));
			
			System.exit(1);
		}
	}
	
	public String getProperty(String key) {
		
		if (key == null || this.properties == null)
			return null;
		
		return this.properties.getProperty(key);
	}
	
	public Integer getIntegerProperty(String key) {
		return Integer.parseInt(this.getProperty(key));
	}	
}
