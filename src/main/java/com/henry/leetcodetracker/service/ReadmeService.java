package com.henry.leetcodetracker.service;

import com.henry.leetcodetracker.model.Problem;
import com.henry.leetcodetracker.util.ConfigUtils;
import com.henry.leetcodetracker.util.FileUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReadmeService {

    private static final String README_FILE = ConfigUtils.getReadmeFilePath();

    public int getNextProblemNo() throws IOException {
        return FileUtils.getNextProblemNo(README_FILE);
    }

    public void appendToReadme(Problem problem, int problemId) throws IOException {
        FileUtils.appendToReadme(problem, problemId);
    }
}
