package com.ikhokha.techcheck;
public class Shaker extends Metric {
    public void incrementCounter(String comment) {
        String words[] = comment.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].toLowerCase().contains("shaker")) {
                this.count++;
            }
        }
    }
}
