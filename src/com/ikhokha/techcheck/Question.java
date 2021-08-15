package com.ikhokha.techcheck;

public class Question extends Metric {
    public void incrementCounter(String comment) {
        if (comment.contains("?")) {
            this.count++;
        }
    }
}
