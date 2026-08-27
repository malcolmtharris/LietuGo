package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static ArrayList<WordPair> loadWordsFromFile(String filename) {
        ArrayList<WordPair> words = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");

                String english = parts[0];
                String lithuanian = parts[1];

                WordPair pair = new WordPair(english, lithuanian);
                words.add(pair);
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find " + filename + " — that category will be empty for now.");
        }

        return words;
    }

    public static int xp = 0;
    public static int streak = 0;
    public static String lastPlayed = "";

    public static void loadStats() throws FileNotFoundException {
        File file = new File("stats.txt");

        if (!file.exists()) {
            return; // no stats yet
        }

        Scanner statsReader = new Scanner(file);

        while (statsReader.hasNextLine()) {
            String line = statsReader.nextLine();
            String[] parts = line.split("=");

            String key = parts[0];
            String value = parts[1];

            if (key.equals("xp")) {
                xp = Integer.parseInt(value);
            } else if (key.equals("streak")) {
                streak = Integer.parseInt(value);
            } else if (key.equals("lastPlayed")) {
                lastPlayed = value;
            }
        }

        statsReader.close();
    }

    public static void saveStats() throws IOException {
        FileWriter writer = new FileWriter("stats.txt");

        writer.write("xp=" + xp + "\n");
        writer.write("streak=" + streak + "\n");
        writer.write("lastPlayed=" + lastPlayed + "\n");

        writer.close();
    }

    public static void main(String[] args) throws IOException {

        loadStats();

        ArrayList<WordPair> greetings = loadWordsFromFile("greetings.txt");
        ArrayList<WordPair> food = loadWordsFromFile("food.txt");
        ArrayList<WordPair> numbers = loadWordsFromFile("numbers.txt");
        ArrayList<WordPair> basicWords = loadWordsFromFile("basicWords.txt");

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

        // save stats
        xp = xp + score;

        // streak logic
        if (lastPlayed.equals("")) {
            streak = 1;
        } else {
            LocalDate previous = LocalDate.parse(lastPlayed);
            LocalDate now = LocalDate.now();

            if (previous.equals(now.minusDays(1))) {
                streak = streak + 1;
            } else if (previous.equals(now)) {
                // already played today
            } else {
                streak = 1;
            }
        }

        lastPlayed = java.time.LocalDate.now().toString();
        saveStats();

        System.out.println("Total XP: " + xp);
        System.out.println("Streak: " + streak + " days");
    }
}