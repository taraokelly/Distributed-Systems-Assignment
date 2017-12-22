package ie.gmit.sw;

import java.util.Map;
import java.util.Queue;

public class Client implements Runnable {

    String uri;
    private volatile Queue<Request> inqueue;
	private Map<String, String> outqueue;

    public Client(String uri, Queue<Request> in, Map<String, String> out){
        this.uri = uri;
        this.inqueue = in;
		this.outqueue = out;
    }

    @Override
	public void run() { 

        while(true){

            Request r = inqueue.poll();

            if(r != null){
                String result = r.doRequest(uri);
                outqueue.put(r.getTaskNumber(), result);
            }
        }
	}
}
