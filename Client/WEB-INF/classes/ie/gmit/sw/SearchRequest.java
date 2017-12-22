package ie.gmit.sw;

import java.rmi.Naming;

public class SearchRequest extends Request{
	
    // Specialized variables and doRequest implementation.
	private String word;
	
	public SearchRequest(String t, String w) {
		super(t);
		this.word = w;
	}

	public String doRequest(String uri) {
		String result;
		try{
			DictionaryService ds = (DictionaryService) Naming.lookup(uri);
			result = ds.search(this.word);
		}catch (Exception e) {
			result = "Uh oh, looks like something went wrong.";
		}
		return result;
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
