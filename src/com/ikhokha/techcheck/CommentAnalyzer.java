package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommentAnalyzer {
	
	private File file;
	
	public CommentAnalyzer(File file) {
		this.file = file;
	}
	
	public Map<String, Integer> analyze() {
		
		Map<String, Integer> resultsMap = new HashMap<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				
				if (numberOfCharacters(line) < 15) {
					incrementOccurrence(resultsMap, "SHORTER_THAN_15", 1);
				} 
				
				int moverMentions = numberOfOccurences(line, "mover");
				if (moverMentions > 0) {
					incrementOccurrence(resultsMap, "MOVER_MENTIONS", moverMentions);
				} 
				
				int shakerMentions = numberOfOccurences(line, "shaker");
				if (shakerMentions > 0) {
					incrementOccurrence(resultsMap, "SHAKER_MENTIONS", shakerMentions);
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		}
		
		return resultsMap;
		
	}
	
	/**
	 * This method increments a counter by a given number of occurences for a match type on the countMap. Uninitialized keys will be set to 1
	 * @param countMap the map that keeps track of counts
	 * @param key the key for the value to increment
	 * @param numberOfOccurences the actual number to increment counter
	 */
	private void incrementOccurrence(Map<String, Integer> countMap, String key, int numberOfOccurences) {
		
		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + numberOfOccurences);
	}

	private int numberOfCharacters(String line) {
		int count = 0;
		for (int i = 0; i < line.length(); i++) {
			if(line.charAt(i) != ' ')    
			count++;  
		}
		return count;
	}

	private int numberOfOccurences(String line, String occurenceText) {
		int count = 0;
		String words[] = line.split(" ");
		for (int i = 0; i < words.length; i++) {
			if(words[i].toLowerCase().contains(occurenceText))    
			{
				count++;  
			}
		}
		return count;
	}
}
