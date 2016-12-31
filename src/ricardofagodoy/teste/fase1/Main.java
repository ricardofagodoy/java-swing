package ricardofagodoy.teste.fase1;

import ricardofagodoy.teste.fase1.util.ApplicationProperties;
import ricardofagodoy.teste.fase1.view.ApplicationFrame;

public class Main {
	
	public static void main (String...args) {
		
		// Retrieve properties instance
		ApplicationProperties properties = ApplicationProperties.getInstance();
		
		new ApplicationFrame(
				properties.getProperty("title"),
				properties.getIntegerProperty("window.width"),
				properties.getIntegerProperty("window.height")
		);
	}
}
