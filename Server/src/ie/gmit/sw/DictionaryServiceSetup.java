package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DictionaryServiceSetup {

	public static void main(String[] args) throws Exception {

		//Read in dictionary
		
		//Create an instance of a FileServiceImpl. As FileServiceImpl implements the FileService
		//interface, it can be referred to as a FileService type.
		
		//Pass in dictionary 
		DictionaryService ds = new DictionaryServiceImpl();

		//Start the RMI registry on port 1099
		LocateRegistry.createRegistry(1099);

		//Bind our remote object to the registry with the human-readable name "fileService"
		Naming.rebind("dictionaryService", ds);

		//Print a message to standard output
		System.out.println("Server ready.");

	}
}
