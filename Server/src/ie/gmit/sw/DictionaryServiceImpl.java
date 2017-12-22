package ie.gmit.sw;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryService {
	private static final long serialVersionUID = 1L;
	private volatile Map<String,String> dictionary;
	ReentrantLock lock = new ReentrantLock();
	
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

	@Override
	public String add(String word, String Description) throws RemoteException {
		lock.lock();
		dictionary.put(word, Description);
		lock.unlock();
		return "Add " + word + " to dictionary successfully.";
		//return "Failed to add " + word + " to dictionary.";
	}

	@Override
	public String modify(String word, String Description) throws RemoteException {
		lock.lock();
		dictionary.put(word, Description);
		lock.unlock();
		return "Modified " + word + " successfully.";
		//return "Failed to modify " + word + " to dictionary.";
	}

	@Override
	public String delete(String word) throws RemoteException {
		lock.lock();
		dictionary.remove(word);
		lock.unlock();
		return "Deleted " + word + " from dictionary successfully.";
		//return "Failed to add " + word + " to dictionary.";
	}

}
