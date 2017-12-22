package ie.gmit.sw;

public abstract class Request implements Requestable{
	
	// Generalized request variables.
	String taskNumber;	
	
	public Request(String t){
		this.taskNumber = t;
	}
	
	// Getters and setters.
	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
	
	// Leave the implementation to to the specialized concrete class.
	public abstract String doRequest(String uri);
	
}
