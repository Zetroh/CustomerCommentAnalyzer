package com.ikhokha.techcheck;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spam extends Metric {
    public void incrementCounter(String comment) {
        if (containsUrl(comment) == true) {
            this.count++;
        }
    }

    private boolean containsUrl(String comment) {
        boolean isSpam = false;
        String words[] = comment.split(" ");
        String regex = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i < words.length; i++) {
            Matcher match = pattern.matcher(words[i]);
            if (match.find()) {
                isSpam = true;
                break;
            }
        }
        return isSpam;
    }
}
