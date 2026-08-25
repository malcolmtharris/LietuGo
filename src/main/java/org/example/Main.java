package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<WordPair> greetings = new ArrayList<>();
        ArrayList<WordPair> food = new ArrayList<>();
        ArrayList<WordPair> numbers = new ArrayList<>();
        ArrayList<WordPair> basicWords = new ArrayList<>();


        // greetings
        File file1 = new File("greetings.txt");
        Scanner fileReader1 = new Scanner(file1);

        while (fileReader1.hasNextLine()) {
            String line = fileReader1.nextLine();
            String[] parts = line.split(",");

            String english = parts[0];
            String lithuanian = parts[1];

            WordPair pair = new WordPair(english, lithuanian);
            greetings.add(pair);
        }

        fileReader1.close();

        // food
        File file2 = new File("food.txt");
        Scanner fileReader2 = new Scanner(file2);

        while (fileReader2.hasNextLine()) {
            String line = fileReader2.nextLine();
            String[] parts = line.split(",");

            String english = parts[0];
            String lithuanian = parts[1];

            WordPair pair = new WordPair(english, lithuanian);
            food.add(pair);
        }

        fileReader2.close();

        // numbers
        File file3 = new File("numbers.txt");
        Scanner fileReader3 = new Scanner(file3);

        while (fileReader3.hasNextLine()) {
            String line = fileReader3.nextLine();
            String[] parts = line.split(",");

            String english = parts[0];
            String lithuanian = parts[1];

            WordPair pair = new WordPair(english, lithuanian);
            numbers.add(pair);
        }

        fileReader3.close();

        // basic words
        File file4 = new File("basicWords.txt");
        Scanner fileReader4 = new Scanner(file4);

        while (fileReader4.hasNextLine()) {
            String line = fileReader4.nextLine();
            String[] parts = line.split(",");

            String english = parts[0];
            String lithuanian = parts[1];

            WordPair pair = new WordPair(english, lithuanian);
            basicWords.add(pair);
        }

        fileReader4.close();

        // category shit
        HashMap<String, ArrayList<WordPair>> categories = new HashMap<>();
        categories.put("1", greetings);
        categories.put("greetings", greetings);

        categories.put("2", food);
        categories.put("food", food);

        categories.put("3", numbers);
        categories.put("numbers", numbers);

        categories.put("4", basicWords);
        categories.put("basicWords", basicWords);

        // category selection
        Scanner input = new Scanner(System.in);

        System.out.println("""
        Choose a category:
        1. Greetings
        2. Food
        3. Numbers
        4. Basic Words""");

        String choice = input.nextLine().toLowerCase();
        ArrayList<WordPair> selectedWords = categories.get(choice);

        if (selectedWords == null) {
            System.out.println("Invalid choice, defaulting to Greetings.");
            selectedWords = greetings;
        }

        Collections.shuffle(selectedWords);

        // main quiz
        ArrayList<WordPair> wrongAnswers = new ArrayList<>();
        int score = 0;
        int retryScore = 0;

        for (WordPair pair : selectedWords) {
            boolean askInLithuanian = Math.random() < 0.5;
            String question;
            String correctAnswer;

            if (askInLithuanian) {
                question = pair.lithuanian;
                correctAnswer = pair.english;
            } else {
                question = pair.english;
                correctAnswer = pair.lithuanian;
            }

            System.out.println("Transalate: " + question);
            String answer = input.nextLine();

            if (answer.equalsIgnoreCase(correctAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The answer was: " + correctAnswer);
                wrongAnswers.add(pair);
            }
        }

        // retry quiz
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