package services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

public class Base64ToImage {
	
	public void Base64DecodeAndSave(String base64String, String imagePath) throws FileNotFoundException, IOException {
		
		String parts[] = base64String.split(",");
		byte[] decodeData =  Base64.getDecoder().decode(parts[1]);
		
		try(OutputStream out = new FileOutputStream(imagePath)){
			out.write(decodeData);
		}
	}
}
