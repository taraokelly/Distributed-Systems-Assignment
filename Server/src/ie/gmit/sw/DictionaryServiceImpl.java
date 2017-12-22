package ie.gmit.sw;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Map;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryService {
	private static final long serialVersionUID = 1L;
	private volatile Map<String,String> dictionary;
	public DictionaryServiceImpl(Map<String,String> dictionary) throws RemoteException{
		super();
		this.dictionary = dictionary;
	}

	@Override
	public String search(String word) throws RemoteException {
		String result = dictionary.get(word);
	    if(result != null)
	    	return result;
	    else if(dictionary.containsKey(word))
	    	return "Word exists with no description.";	  
	    return "Word not found.";	  
	}

}
