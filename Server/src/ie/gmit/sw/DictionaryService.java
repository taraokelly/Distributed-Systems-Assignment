package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DictionaryService extends Remote{
	
	public String search(String word) throws RemoteException;
	
	public String add(String word, String Description) throws RemoteException;
	  
	public String modify(String word, String Description) throws RemoteException;
	 
	public String delete(String word) throws RemoteException;
}
