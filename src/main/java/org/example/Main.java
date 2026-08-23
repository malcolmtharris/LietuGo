package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<WordPair> words = new ArrayList<>();

        words.add(new WordPair("Hello", "Labas"));
        words.add(new WordPair("Thank you", "Ačiū"));
        words.add(new WordPair("Yes", "Taip"));
        words.add(new WordPair("No", "Ne"));
        words.add(new WordPair("Please / Your welcome", "Prašau"));
        words.add(new WordPair("Okay / Good", "Gerai"));
        words.add(new WordPair("Good morning", "Labas rytas"));
        words.add(new WordPair("Good evening", "Labas vakaras"));
        words.add(new WordPair("Bye", "Iki"));

        Scanner input = new Scanner(System.in);
        int score = 0;

        for (WordPair pair : words) {
            System.out.println("Transalate: " + pair.english);
            String answer = input.nextLine();

            if (answer.equalsIgnoreCase(pair.lithuanian)) {
                System.out.println("Correct!");
                score++;
            } else  {
                System.out.println("Wrong! The answer was: " + pair.lithuanian);
            }
        }

        System.out.println("Your score " + score + " out of " + words.size());
    }
}