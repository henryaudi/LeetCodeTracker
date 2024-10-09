package com.henry.leetcodetracker.service;

import com.henry.leetcodetracker.model.Problem;
import com.henry.leetcodetracker.util.FileUtils;

import java.io.File;
import java.io.IOException;

public class ProblemService {

    /**
     * Extract and build Problem object from javaFile given
     * @param javaFile
     * @return
     * @throws IOException
     */
    public Problem extractFromFile(File javaFile) throws IOException {
        return FileUtils.extractProblemFromFile(javaFile);
    }


}
