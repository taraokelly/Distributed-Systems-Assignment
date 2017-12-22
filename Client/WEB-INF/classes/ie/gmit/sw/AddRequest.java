package ie.gmit.sw;

public class AddRequest extends Request{
	
	private String word;
	private String description;
	
	public AddRequest(String t, String w, String d) {
		super(t);
		this.word = w;
		this.description = d;
	}

	public String doRequest(String uri) {
		return null;
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
