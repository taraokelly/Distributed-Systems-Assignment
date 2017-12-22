package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class DictionaryReader {
	
	String fileName;
	
	public DictionaryReader(String fileName) {
		this.fileName = fileName;
	}
	
	public Map<String, String> load(){
		try{
			Map<String,String> dictionary = new HashMap<String, String>();
			InputStream in = getClass().getResourceAsStream(fileName);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
			
			while(true){
			   String line = bufferedReader.readLine();
			    // Break if line is empty. 
			    if(line == null) break;
			    String[] split = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			    dictionary.put(split[0], split[1]);
			}
			// Clean up.
			bufferedReader.close();	
			return dictionary;
		}catch(Exception e){
			e.printStackTrace();
		}
		 return null;
	}

}
