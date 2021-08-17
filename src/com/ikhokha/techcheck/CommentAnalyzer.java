package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

public class CommentAnalyzer implements Supplier<Map<String, Integer>> {
	
	private File file;
	
	public CommentAnalyzer(File file) {
		this.file = file;
	}
	
	public Map<String, Integer> analyze() throws InterruptedException {
		ShortComment shortComment = new ShortComment();
		Mover mover = new Mover();
		Shaker shaker = new Shaker();
		Question question = new Question();
		Spam spam = new Spam();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				shortComment.incrementCounter(line);
				mover.incrementCounter(line);
				shaker.incrementCounter(line);
				question.incrementCounter(line);
				spam.incrementCounter(line);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		}
		
		return Map.of(
			"SHORTER_THAN_15", shortComment.getCount(), 
			"MOVER_MENTIONS", mover.getCount(), 
			"SHAKER_MENTIONS", shaker.getCount(),
			"QUESTIONS", question.getCount(),
			"SPAM", spam.getCount()
			);	
	}

	@Override
	public Map<String, Integer> get() {
		try {
			return analyze();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Map.of();
	}
}
