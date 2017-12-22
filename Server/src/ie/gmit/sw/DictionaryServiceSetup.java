package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Map;

public class DictionaryServiceSetup {
	
	public static void main(String[] args) throws Exception {

		//Read in dictionary
		DictionaryReader dr = new DictionaryReader("dictionary.csv");
		Map<String,String> d = dr.load();
		
		//Pass in dictionary 
		DictionaryService ds = new DictionaryServiceImpl(d);

		//Start the RMI registry on port 1099
		LocateRegistry.createRegistry(1099);

		//Bind our remote object to the registry with the human-readable name "dictionaryService"
		Naming.rebind("dictionaryService", ds);

		//Print a message to standard output
		System.out.println("Server ready.");

	}
}
