package com.henry.leetcodetracker.service;

import com.henry.leetcodetracker.model.Problem;
import com.henry.leetcodetracker.util.ConfigUtils;
import com.henry.leetcodetracker.util.FileUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReadmeService {

    private static final String README_FILE = ConfigUtils.getReadmeFilePath();

    public void appendToReadme(Problem problem, int problemNo) throws IOException {

        String newProblemRow = String.format("| %d %s", problemNo, problem.toMarkdownRow());

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(README_FILE, true))) {
            writer.write(newProblemRow);
        }

        // Print system notice
        System.out.println("Append problem #" + problem.getProblemId() + " successfully!");
    }

    public int getNextProblemNo() throws IOException {
        return FileUtils.getNextProblemNo(README_FILE);
    }
}
