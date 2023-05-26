package com.example.languagelearning;
import java.io.*;
import java.util.*;

// A class to represent a word with its translation and group


// A class to write and read data to and from a csv file
public class CSVHandler {

    // A method to write a list of words to a csv file
    public static void writeData(List<Word> words, String fileName) {
        try {
            // Create a file writer object
            FileWriter writer = new FileWriter(fileName);

            // Write the header row
            writer.write("Word,Translation,Group\n");

            // Loop through the list of words and write each one as a row
            for (Word word : words) {
                writer.write(word.toString() + "\n");
            }

            // Close the writer
            writer.close();
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }

    // A method to read data from a csv file and return a list of words
    public static List<Word> readData(String fileName) {
        // Create an empty list to store the words
        List<Word> words = new ArrayList<>();

        try {
            // Create a file reader object
            FileReader reader = new FileReader(fileName);

            // Create a buffered reader object to read line by line
            BufferedReader bufferedReader = new BufferedReader(reader);

            // Skip the header row
            bufferedReader.readLine();

            // Read each line until the end of file
            String line = bufferedReader.readLine();
            while (line != null) {
                // Split the line by comma and store the values in an array
                String[] values = line.split(",");

                // Create a word object from the array values
                Word word = new Word(values[0], values[1], values[2]);

                // Add the word to the list
                words.add(word);

                // Read the next line
                line = bufferedReader.readLine();
            }

            // Close the reader
            bufferedReader.close();
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }

        // Return the list of words
        return words;
    }

    public static List<Word> getWordsByGroup(List<Word> words, String group) {
        // Create an empty list to store the matching words
        List<Word> result = new ArrayList<>();

        // Loop through the list of words and check their group
        for (Word word : words) {
            // If the word's group matches the given group, add it to the result list
            if (word.getGroup().equals(group)) {
                result.add(word);
            }
        }

        // Return the result list
        return result;
    }
}
