package com.henry.leetcodetracker.util;

import com.henry.leetcodetracker.model.Problem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static Problem extractProblemFromFile(File javaFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(javaFile));
        String line;
        String problemId = null,
                problemName = null,
                difficulty = null,
                topics = null,
                date = null,
                notes = null;

        // Read the Java file and look for the tags in the docstring
        while ((line = reader.readLine()) != null) {
            if (line.contains("@problemId")) {
                problemId = line.split("@problemId")[1].trim();
            } else if (line.contains("@problemName")) {
                problemName = line.split("@problemName")[1].trim();
            } else if (line.contains("@difficulty")) {
                difficulty = line.split("@difficulty")[1].trim();
            } else if (line.contains("@topics")) {
                topics = line.split("@topics")[1].trim();
            } else if (line.contains("@date")) {
                date = line.split("@date")[1].trim();
            } else if (line.contains("@Notes")) {
                String[] lineTokens = line.split("@Notes");
                if (lineTokens.length > 1) {
                    notes = lineTokens[1].trim();
                }
            }
        }

        reader.close();

        // Check if all necessary details are extracted
        if (problemId == null
                || problemName == null
                || difficulty == null) {
            System.err.println("Error: Failed to extract problem metadata from " + javaFile.getName());
            return null;
        }

        // Split the topics string by commas and trim extra spaces.
        List<String> topicList = new ArrayList<>();
        if (topics != null) {
            topicList = Arrays.asList(topics.split("\\s*,\\s*"));
        }

        return new Problem(problemId, problemName, difficulty, date, notes, topicList);
    }

    public static int getNextProblemNo(String readmeFile) throws IOException {

        int lastCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(readmeFile))) {
            String line;
            String lastLine = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("|")) {
                    lastLine = line;
                }
            }

            if (lastLine != null) {
                String[] columns = lastLine.split("\\|");
                if (columns.length > 1) {
                    lastCount = Integer.parseInt(columns[1].trim());
                }
            }
        }

        return lastCount + 1;
    }
}
