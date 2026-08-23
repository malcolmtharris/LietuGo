package org.example;

public class WordPair {
    String english;
    String lithuanian;

    public WordPair(String english, String lithuanian) {
        this.english = english;
        this.lithuanian = lithuanian;
    }

    @Override
    public String toString() {
        return english + " -> " + lithuanian;
    }
}
