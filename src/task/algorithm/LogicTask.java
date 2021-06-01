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

	private static String outputString;
	private static int globalCount;
	private static int totalCount;
	private static Map<String, Integer> mapa;
	
	
	public static String start(boolean direct, String input) {
		
		if (input.length() == 0) return "Error, empty input";
		
		globalCount = 0;
		totalCount = 0;
		mapa = new HashMap<>();
		
		if (direct) {
			algorithm(input);
			return prepareOutput();
		}
		else {
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
				
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			
		}

		return prepareOutput();

	}
	
	private static void algorithm(String input) {
		
		String[] split = input.split(" ");
		
		for (String x : split) {
			
			int localCount = 0;
			boolean[] containsLogic = new boolean[5];
			for (int i = 0; i < 5; ++i) {
				containsLogic[i] = false;
			}
			
			x = x.toLowerCase();
			int trueLen = x.length();
			for (int i = 0; i < x.length(); ++i) {
				char c = x.charAt(i);
				if (!Character.isLetterOrDigit(c)) trueLen--;
				else if (c == 'l') {
					globalCount++;
					localCount++;
					containsLogic[0] = true;
				} else if (c == 'o') {
					globalCount++;
					localCount++;
					containsLogic[1] = true;
				} else if (c == 'g') {
					globalCount++;
					localCount++;
					containsLogic[2] = true;
				} else if (c == 'i') {
					globalCount++;
					localCount++;
					containsLogic[3] = true;
				} else if (c == 'c') {
					globalCount++;
					localCount++;
					containsLogic[4] = true;
				}
			}
			
			if (localCount > 0) {
				String key = generateKey(containsLogic, trueLen);
				if (mapa.containsKey(key)) mapa.put(key, mapa.get(key) + localCount);
				else mapa.put(key, localCount);
			}
			
			totalCount += trueLen;
		}
		
		return;
	}
	
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
		
		outputString += "Total freq: " + Math.round(1.0*globalCount/totalCount*100.0)/100.0
				+ " (" + globalCount + "/" + totalCount + ")" + "\n";
		
		return outputString;
	}
	
	private static String generateKey(boolean[] containsLogic, int count) {
		
		String key = "[";
		if (containsLogic[0]) key = key + "l, ";
		if (containsLogic[1]) key = key + "o, ";
		if (containsLogic[2]) key = key + "g, ";
		if (containsLogic[3]) key = key + "i, ";
		if (containsLogic[4]) key = key + "c, ";
		key = key + Integer.toString(count);
		key += "]";
		return key;
	}
	
}
