package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<WordPair> greetings = new ArrayList<>();
        ArrayList<WordPair> food = new ArrayList<>();
        ArrayList<WordPair> numbers = new ArrayList<>();
        ArrayList<WordPair> basicWords = new ArrayList<>();

        // greetings
        greetings.add(new WordPair("Hello", "Labas"));
        greetings.add(new WordPair("Good morning", "Labas rytas"));
        greetings.add(new WordPair("Good evening", "Labas vakaras"));
        greetings.add(new WordPair("Bye", "Iki"));

        // basic words
        basicWords.add(new WordPair("Thank you", "Ačiū"));
        basicWords.add(new WordPair("Yes", "Taip"));
        basicWords.add(new WordPair("No", "Ne"));
        basicWords.add(new WordPair("Please / Your welcome", "Prašau"));
        basicWords.add(new WordPair("Okay / Good", "Gerai"));
        basicWords.add(new WordPair("Bad", "Blogai"));
        basicWords.add(new WordPair("Sorry", "Atsiprašau"));
        basicWords.add(new WordPair("Excuse me", "Atsiprašau"));
        basicWords.add(new WordPair("I don't understand", "Aš nesuprantu"));
        basicWords.add(new WordPair("Do you speak English?", "Ar kalbate angliškai?"));
        basicWords.add(new WordPair("My name is...", "Mano vardas..."));
        basicWords.add(new WordPair("How are you?", "Kaip sekasi?"));
        basicWords.add(new WordPair("I love you", "Aš tave myliu"));
        basicWords.add(new WordPair("What is this?", "Kas tai yra?"));
        basicWords.add(new WordPair("Where is...?", "Kur yra...?"));
        basicWords.add(new WordPair("Help", "Pagalba"));
        basicWords.add(new WordPair("I", "Aš"));
        basicWords.add(new WordPair("You", "Tu"));

        // food
        food.add(new WordPair("Bread", "Duona"));
        food.add(new WordPair("Water", "Vanduo"));
        food.add(new WordPair("Milk", "Pienas"));
        food.add(new WordPair("Cheese", "Sūris"));
        food.add(new WordPair("Apple", "Obuolys"));
        food.add(new WordPair("Meat", "Mėsa"));
        food.add(new WordPair("Fish", "Žuvis"));
        food.add(new WordPair("Egg", "Kiaušinis"));
        food.add(new WordPair("Coffee", "Kava"));
        food.add(new WordPair("Tea", "Arbata"));

        // numbers
        numbers.add(new WordPair("One", "Vienas"));
        numbers.add(new WordPair("Two", "Du"));
        numbers.add(new WordPair("Three", "Trys"));
        numbers.add(new WordPair("Four", "Keturi"));
        numbers.add(new WordPair("Five", "Penki"));
        numbers.add(new WordPair("Six", "Šeši"));
        numbers.add(new WordPair("Seven", "Septyni"));
        numbers.add(new WordPair("Eight", "Aštuoni"));
        numbers.add(new WordPair("Nine", "Devyni"));
        numbers.add(new WordPair("Ten", "Dešimt"));

        Scanner input = new Scanner(System.in);

        int score = 0;
        int retryScore = 0;

        System.out.println("Choose a category:\n" +
                "1. Greetings\n" +
                "2. Food\n" +
                "3. Numbers\n" +
                "4. Basic Words");

        String choice = input.nextLine();

        ArrayList<WordPair> selectedWords;

        if (choice.equals("1")) {
            selectedWords = greetings;
        } else if (choice.equals("2")) {
            selectedWords = food;
        } else if (choice.equals("3")) {
            selectedWords = numbers;
        } else {
            selectedWords = basicWords;
        }

        Collections.shuffle(selectedWords);

        ArrayList<WordPair> wrongAnswers = new ArrayList<>();

        for (WordPair pair : selectedWords) {
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

        System.out.println("Your score " + score + " out of " + selectedWords.size());
        double percentage = (double) score / selectedWords.size() * 100;
        System.out.println("Percentage: " + percentage + "%");

        System.out.println("Retry score: " + retryScore + " out of " + wrongAnswers.size());

    }
}