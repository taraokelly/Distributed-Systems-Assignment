package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DictionaryService extends Remote{
	
	public String search(String filename) throws RemoteException;
}
