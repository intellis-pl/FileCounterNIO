package main.java.helpers;


import main.java.dto.ResultFilesDTO;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public final class ResultFilesHelper {

    private static final PathMatcher MATCHER;

    static {
        MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.xml");
    }

    public static ResultFilesDTO resetCurrentDirFilesAmount(ResultFilesDTO resultFiles) {
        resultFiles.setCurrentDirFilesAmount(0);
        return resultFiles;
    }

    public static Boolean isMatchFile(Path file) {
        return MATCHER.matches(file);
    }
}
