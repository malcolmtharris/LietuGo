package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

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

        Collections.shuffle(words);

        Scanner input = new Scanner(System.in);
        int score = 0;
        int retryScore = 0;

        ArrayList<WordPair> wrongAnswers = new ArrayList<>();

        for (WordPair pair : words) {
            System.out.println("Transalate: " + pair.english);
            String answer = input.nextLine();

            if (answer.equalsIgnoreCase(pair.lithuanian)) {
                System.out.println("Correct!");
                score++;
            } else  {
                System.out.println("Wrong! The answer was: " + pair.lithuanian);
                wrongAnswers.add(pair);
            }
        }

        if (wrongAnswers.size() > 0) {
            System.out.println("Time to retry the words you failed...");
            for (WordPair pair : wrongAnswers) {
                System.out.println("Translate: " + pair.english);
                String answer = input.nextLine();

                if (answer.equalsIgnoreCase(pair.lithuanian)) {
                    System.out.println("Correct!");
                    retryScore++;
                } else {
                    System.out.println("Wrong! The answer was: " + pair.lithuanian);
                }
            }
        }

        System.out.println("Your score " + score + " out of " + words.size());
        double percentage = (double) score / words.size() * 100;
        System.out.println("Percentage: " + percentage + "%");

        System.out.println("Retry score: " + retryScore + " out of " + wrongAnswers.size());

    }
}