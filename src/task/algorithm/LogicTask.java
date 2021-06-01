package task.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LogicTask {

	public static String start() {
		
		String input = "I love to work in global logic!";
		String[] split = input.split(" ");
		String output = "";
		
		int globalCount = 0;
		int totalCount = 0;
		Map<String, Integer> mapa = new HashMap<>();
		
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
		
		Map<String, Double> freq = new HashMap<>();
		for (String k : mapa.keySet()) {
			freq.put(k, (double)mapa.get(k)/globalCount);
		}
		
		List<Entry<String, Double>> sorted = new ArrayList<>(freq.entrySet());
		sorted.sort(Entry.comparingByValue());
		
		for (Entry<String, Double> x : sorted) {
			System.out.println(x.getKey() + " = " + Math.round(freq.get(x.getKey())*100.0)/100.0
			+ " (" + mapa.get(x.getKey()) + "/" + globalCount + ")");
			output += x.getKey() + " = " + Math.round(freq.get(x.getKey())*100.0)/100.0
					+ " (" + mapa.get(x.getKey()) + "/" + globalCount + ")" + "\n";
		}
		
		
		System.out.println("Total freq: " + Math.round(1.0*globalCount/totalCount*100.0)/100.0
				+ " (" + globalCount + "/" + totalCount + ")");
		output += "Total freq: " + Math.round(1.0*globalCount/totalCount*100.0)/100.0
				+ " (" + globalCount + "/" + totalCount + ")" + "\n";
		
		return output;

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
