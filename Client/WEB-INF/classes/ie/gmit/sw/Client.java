package ie.gmit.sw;

import java.rmi.Naming;
import java.util.Map;
import java.util.Queue;

//implement runnable
public class Client {

    String uri;
    Request r;

    public Client(String uri, Request r){
        //pass in address here
        this.uri = uri;
        this.r = r;
    }

    //@Override
	//public void run() { }
	public String getDesc(){
		
        /*
        //Ask the registry running on localhost and listening in port 1099 for the instance of
		//the FileService object that is bound to the RMI registry with the name fileService.
		DictionaryService ds = (DictionaryService) Naming.lookup(uri);

		//Make a remote method invocation to ask for the list of files
		//The ArrayList of file names is transferred over the network in serialized form
		String desc = ds.search(str);*/

        String result = r.doRequest(uri);

		return result;
	}

}
