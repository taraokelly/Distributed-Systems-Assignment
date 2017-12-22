package ie.gmit.sw;

import java.rmi.Naming;
import java.util.Map;
import java.util.Queue;

//implement runnable
public class Client {

    String uri;
    private Queue<Request> inqueue;
	private Map<String, String> outqueue;

    public Client(String uri, Queue<Request> in, Map<String, String> out){
        //pass in address here
        this.uri = uri;
        this.inqueue = in;
		this.outqueue = out;

    }

    //@Override
	//public void run() { }
	public void getDesc() {

        Request r = inqueue.poll();

        if(r != null){
            String result = r.doRequest(uri);
            outqueue.put(r.getTaskNumber(), result);
        }

		//return result;
	}

}
