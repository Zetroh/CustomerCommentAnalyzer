package com.ikhokha.techcheck;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		
		Map<String, Integer> totalResults = new HashMap<>();
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for (File commentFile : commentFiles) {
			CompletableFuture<Map<String, Integer>> futureResult = CompletableFuture
					.supplyAsync(new CommentAnalyzer(commentFile), executor);
			addReportResults(futureResult.get(), totalResults);
		}
		
		executor.shutdown();
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}
	
	/**
	 * This method adds the result counts from a source map to the target map 
	 * @param source the source map
	 * @param target the target map
	 */
	private static void addReportResults(Map<String, Integer> source, Map<String, Integer> target) {
		for (Map.Entry<String, Integer> entry : source.entrySet()) {
			if (target.containsKey(entry.getKey()) == false) {
				target.put(entry.getKey(), entry.getValue());
			} else {
				int currentTargetValue = target.get(entry.getKey());
				target.put(entry.getKey(), entry.getValue() + currentTargetValue);
			}
		}
	}
}
