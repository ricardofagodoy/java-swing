package ricardofagodoy.teste.fase1.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.ImageIcon;

public class ImageUtils {
	
	private static final int IMG_BUFFER = 2048;
	
	public static ImageIcon urlToImageIcon(String url) {
		
		long start = System.currentTimeMillis();
		
		ImageIcon result = null;
		
		if (url != null)
			result = new ImageIcon(urlToBytes(url));
		
		System.out.println("Loading image took " + (System.currentTimeMillis() - start) + " ms");
		return result;
	}
	
	public static byte[] urlToBytes(String url) {

		byte[] result = null;
		
		try {
			
			URL imageUrl = new URL(url);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream is = null;

			  is = imageUrl.openStream();
			  byte[] byteChunk = new byte[IMG_BUFFER];
			  int n;

			  while ((n = is.read(byteChunk)) > 0)
			    baos.write(byteChunk, 0, n);
			  
			  result = baos.toByteArray();
	        
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

        return result;
	}
}
