package ricardofagodoy.teste.fase1.util;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtils {
	
	private static HttpClient client;
	
	static {
		client = HttpClientBuilder.create().build();
	}
	
	// Gets on url and returns its string content
	public static String getUrl(String url) {
		
		String result = "";
		HttpGet request = new HttpGet(url);
		
		try {
			HttpResponse response = client.execute(request);
			result = EntityUtils.toString(response.getEntity());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// Returns Json-Object parsed A based on url string content
	public static <A> A getJsonList(String url, String propertyName, Class<A> type) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return mapper.readValue(getUrl(url), type);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
}
