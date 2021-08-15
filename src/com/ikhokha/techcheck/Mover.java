package com.ikhokha.techcheck;

public class Mover extends Metric {
    public void incrementCounter(String comment) {
        String words[] = comment.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].toLowerCase().contains("mover")) {
                this.count++;
            }
        }
    }
}
