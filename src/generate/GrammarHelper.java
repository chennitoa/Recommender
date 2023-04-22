package generate;

import java.util.List;

public class GrammarHelper {
	
	
	public static String generateCommaSeperatedList(List<String> words) {
		String finalWord = "";
		switch (words.size()) {
			case 2:
				finalWord += words.get(0) + " and " + words.get(1);
				break;
			case 1:
				finalWord += words.get(0);
				break;
			case 0:
				finalWord = "%delete%";
				break;
			default:
				for (int i = 0; i < words.size() - 1; i++) {
					finalWord += words.get(i) + ", ";
				}
				finalWord += "and " + words.get(words.size() - 1);
				break;
		}
		return finalWord;
	}
	
	public static String generateCommaSeperatedListWithQuotes(List<String> words) {
		String finalWord = "";
		switch (words.size()) {
			case 2:
				finalWord += "\"" + words.get(0) + "\" and \"" + words.get(1) + "\"";
				break;
			case 1:
				finalWord += "\"" + words.get(0) + "\"";
				break;
			case 0:
				finalWord = "%delete%";
				break;
			default:
				for (int i = 0; i < words.size() - 1; i++) {
					finalWord += "\"" + words.get(i) + "\", ";
				}
				finalWord += "and \"" + words.get(words.size() - 1) + "\"";
				break;
		}
		return finalWord;
	}
	
}
