package com.ikhokha.techcheck;
public class ShortComment extends Metric {
    public void incrementCounter(String comment) {
        if (numberOfCharacters(comment) < 15) {
            this.count++;
        }
    }

    private int numberOfCharacters(String comment) {
        int countChar = 0;
        for (int i = 0; i < comment.length(); i++) {
            if (comment.charAt(i) != ' ')
                countChar++;
        }
        return countChar;
    }
}