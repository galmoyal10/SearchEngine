import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.queryparser.classic.QueryParser;

/**
 * represents a single document
 */
public class DocumentInstance {
	public DocumentInstance(int docId, int label, String content) {
		this.docId = docId;
		this.label = label;
		this.content = content;
	}
	
	public DocumentInstance(DocumentInstance other) {
		this.docId = other.docId;
		this.label = other.label;
		this.content = other.content;
	}
	
	public int docId;
	public int label;
	public String content;
	
	
	public void normalize(HashMap<String, String> normalizingStrings) throws IOException
	{
		this.content = normalizeString(this.content, normalizingStrings);
	}
	
	/**
	 * normalizing method used for both documents and queries
	 * @param input - the string to normalize
	 * @return the normalized string
	 * @throws IOException
	 */
	private String normalizeString(String input, HashMap<String, String> normalizingStrings) throws IOException {
		input = input.toLowerCase();
		for (Map.Entry<String, String> e : normalizingStrings.entrySet()) {
			input = input.replaceAll(e.getKey(), e.getValue());
		}
		return QueryParser.escape(input);
	}
	
}
