package ie.gmit.sw;

import java.rmi.Naming;

public class AddRequest extends Request{
	
	private String word;
	private String description;
	
	public AddRequest(String t, String w, String d) {
		super(t);
		this.word = w;
		this.description = d;
	}

	public String doRequest(String uri) {
		String result;
		try{
			DictionaryService ds = (DictionaryService) Naming.lookup(uri);
			result = ds.add(this.word,this.description);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
/*
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

*/