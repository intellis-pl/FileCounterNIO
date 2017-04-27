package main.java.helpers;


import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public class FileMatcher {
    private static final PathMatcher MATCHER;

    static {
        MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.xml");
    }

    public static Boolean isFileMatch(Path file) {
        return MATCHER.matches(file);
    }
}
