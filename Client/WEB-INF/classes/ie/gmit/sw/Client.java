package ie.gmit.sw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.Naming;
import java.util.ArrayList;

public class Client {

    public Client(){}

	public String getDesc(String str) throws Exception {
		//Ask the registry running on localhost and listening in port 1099 for the instance of
		//the FileService object that is bound to the RMI registry with the name fileService.
		DictionaryService ds = (DictionaryService) Naming.lookup("rmi://127.0.0.1:1099/dictionaryService");

		//Make a remote method invocation to ask for the list of files
		//The ArrayList of file names is transferred over the network in serialized form
		String desc = ds.search(str);

		return desc;
	}

}
