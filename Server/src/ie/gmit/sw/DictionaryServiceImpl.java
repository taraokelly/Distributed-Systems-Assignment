package ie.gmit.sw;

import java.rmi.*;
import java.rmi.server.*;
public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryService {
	private static final long serialVersionUID = 1L;

	public DictionaryServiceImpl() throws RemoteException{
		super();
	}

	@Override
	public String search(String filename) throws RemoteException {
		return "DoriTOE";
	}

}
