package com.henry.leetcodetracker.model;

import java.util.ArrayList;
import java.util.List;

public class Problem {

    // Data Fields
    private String problemId;
    private String problemName;
    private String difficulty;
    private String date;
    private String note;
    private List<String> topics;

    // Constructor
    public Problem(String problemId,
                   String problemName,
                   String difficulty,
                   String date,
                   String note,
                   List<String> topics) {

        this.problemId = problemId;
        this.problemName = problemName;
        this.difficulty = difficulty;
        this.date = date;
        this.note = note;
        this.topics = new ArrayList<String>(topics);
    }

    // Setters and Getters
    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    // Methods

    /**
     * Convert the problem to a markdown row.
     * @return a string of the problem with its fields populated
     */
    public String toMarkdownRow() {
        return String.format("| %s | %s | %s | %s | %s | %s |\n",
                problemId,
                problemName,
                difficulty,
                String.join(", ", topics),
                date,
                note);
    }
}
