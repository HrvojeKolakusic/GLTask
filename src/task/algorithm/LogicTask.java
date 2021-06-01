package task.algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LogicTask {

	private static String outputString;			// output string
	private static int globalCount;				// total number of characters in the input from the chars list
	private static int totalCount;				// total number of alphanumeric characters in the input
	private static Map<String, Integer> mapa; 	// map that stores the data needed to generate an output
	private static ArrayList<Character> chars;	// list that stores the characters the program has to look for and count
	
	/**
	 * responses to the Start button and starts the algorithm
	 * @param direct boolean to check the type of input
	 * @param input direct input or path to file
	 * @param charList list of characters to look for and count
	 * @return output string
	 */
	public static String start(boolean direct, String input, String charList) {
		
		if (input.length() == 0) return "Error, empty input";
		
		globalCount = 0;
		totalCount = 0;
		mapa = new HashMap<>();
		chars = new ArrayList<>();
		
		String[] split = charList.split(" ");
		for (String c : split) {
			chars.add(c.charAt(0));
		}		
		
		if (direct) {
			algorithm(input);
			if (totalCount == 0) return "There is no alphanumeric characters in the input";
			if (globalCount == 0) return "There is no characters from the list in the input";
			return prepareOutput();
		} else {
			try {
				Path path = Paths.get(input);
				if (!Files.exists(path)) return "Error, file not found";
				BufferedReader br = new BufferedReader(
						new InputStreamReader(
								new BufferedInputStream(
										new FileInputStream(path.toString()))));
				
				String line;
				while ((line=br.readLine()) != null) {
					algorithm(line);
				}
				
				br.close();
				
				if (totalCount == 0) return "There is no alphanumeric chars in the input";
				if (globalCount == 0) return "There is no chars from the list in the input";
				return prepareOutput();
				
			} catch (IOException e) {
				e.printStackTrace();
				return "Exception error!";
			}
			
		}

	}
	
	/**
	 * looks for and counts the combinations of characters  from the chars list
	 * @param input string
	 */
	private static void algorithm(String input) {
		
		String[] split = input.split(" ");
		
		for (String x : split) {
			
			int localCount = 0;
			boolean[] containsChars = new boolean[chars.size()];
			for (int i = 0; i < chars.size(); ++i) {
				containsChars[i] = false;
			}
			
			x = x.toLowerCase();
			int trueLen = x.length();
			for (int i = 0; i < x.length(); ++i) {
				char c = x.charAt(i);
				if (!Character.isLetterOrDigit(c)) trueLen--;
				else if (chars.contains(c)) {
					globalCount++;
					localCount++;
					containsChars[chars.indexOf(c)] = true;
				}
			}
			
			if (localCount > 0) {
				String key = generateKey(containsChars, trueLen);
				if (mapa.containsKey(key)) mapa.put(key, mapa.get(key) + localCount);
				else mapa.put(key, localCount);
			}
			
			totalCount += trueLen;
		}
		
		return;
	}
	
	/**
	 * prepares the output string by calculating and sorting the frequencies
	 * @return string
	 */
	private static String prepareOutput() {
		
		outputString = "";
		Map<String, Double> freq = new HashMap<>();
		for (String k : mapa.keySet()) {
			freq.put(k, (double)mapa.get(k)/globalCount);
		}
		
		List<Entry<String, Double>> sorted = new ArrayList<>(freq.entrySet());
		sorted.sort(Entry.comparingByValue());
		
		for (Entry<String, Double> x : sorted) {
			outputString += x.getKey() + " = " + Math.round(freq.get(x.getKey())*100.0)/100.0
					+ " (" + mapa.get(x.getKey()) + "/" + globalCount + ")" + "\n";
		}
		
		outputString += "Total frequency: " + Math.round(1.0*globalCount/totalCount*100.0)/100.0
				+ " (" + globalCount + "/" + totalCount + ")" + "\n";
		
		return outputString;
	}
	
	/**
	 * generates a key for the map based on which chars from the list does it contain and how many
	 * @param containsChars field that keep track which chars from the list does the word contain
	 * @param count how many chars from the list does the word contain
	 * @return return a string that acts as a key, e.g. [l, o, g, 6]
	 */
	private static String generateKey(boolean[] containsChars, int count) {
		
		String key = "[";
		
		for (int i = 0; i < chars.size(); ++i) {
			if (containsChars[i]) key += chars.get(i) + ", ";
		}
		
		key += Integer.toString(count);
		key += "]";
		return key;
	}
	
}
