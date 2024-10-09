package com.henry.leetcodetracker.util;

import com.henry.leetcodetracker.model.Problem;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    private static final String README_FILE = ConfigUtils.getReadmeFilePath();

    public static void appendToReadme(Problem problem, int problemNo) throws IOException {

        String newProblemRow = String.format("| %d %s", problemNo, problem.toMarkdownRow());

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(README_FILE, true))) {
            writer.write(newProblemRow);
        }

        // Print system notice
        System.out.println("Append problem #" + problem.getProblemId() + " successfully!");
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
