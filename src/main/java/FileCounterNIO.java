package main.java;

import main.java.dto.DirectoryFilesAmountDTO;
import main.java.dto.ResultFilesDTO;
import main.java.search.ThreeWalker;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCounterNIO {
    private final static Logger LOGGER = Logger.getLogger(FileCounterNIO.class);

    public static void main(String[] args) {

        Path searchFile = Paths.get("./search_dir/A");

        try {
            ThreeWalker search = new ThreeWalker();
            Files.walkFileTree(searchFile, search);

            ResultFilesDTO resultFiles = search.getResultFiles();
            printResults(resultFiles);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static void printResults(ResultFilesDTO resultFiles) {
        for(DirectoryFilesAmountDTO filesAmount: resultFiles.getFilesAmountPerDirectoryBranch()) {
            LOGGER.info(filesAmount.getDirectoryName() + ": " + filesAmount.getFileAmount());
        }

        LOGGER.info("\nAll files: " + resultFiles.getAllFilesAmount());
    }
}
