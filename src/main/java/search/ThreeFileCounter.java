package main.java.search;

import main.java.dto.DirectoryFilesAmountDTO;
import main.java.dto.ResultFilesDTO;
import main.java.helpers.FileMatcher;
import main.java.helpers.TemporaryResultFilesManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;


public class ThreeFileCounter implements FileVisitor<Path> {
    private final static Logger LOGGER = Logger.getLogger(ThreeFileCounter.class);

    private Integer allFilesAmount;
    private Integer currentDirFilesAmount;
    private ResultFilesDTO resultFiles;
    private TemporaryResultFilesManager temporaryResultFiles;

    {
        resultFiles = new ResultFilesDTO(0, new LinkedList<>());
        temporaryResultFiles = new TemporaryResultFilesManager();
        allFilesAmount = 0;
        currentDirFilesAmount = 0;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        String dirName = dir.toString();
        temporaryResultFiles.registerTempDirectory(dirName);
        resetCurrentDirFilesAmount();
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path currentFile, BasicFileAttributes attrs) throws IOException {
        if (FileMatcher.isFileMatch(currentFile)) {
            incrementFilesAmount();
            saveAllFilesAmount();
            temporaryResultFiles.updateFilesAmountForParents();
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        LOGGER.error(exc.getMessage());
        return FileVisitResult.TERMINATE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path currentFile, IOException exc) throws IOException {
        String dirName = currentFile.toString();
        saveFilesAmountForCurrentDirectory(dirName);
        temporaryResultFiles.unregisterTempDirectory(dirName);
        return FileVisitResult.CONTINUE;
    }

    public ResultFilesDTO getResultFiles() {
        return resultFiles;
    }

    private void saveFilesAmountForCurrentDirectory(String dirName) {
        Integer filesInCurrDir = temporaryResultFiles.findAmountForDirectory(dirName);
        resultFiles.addFilesAmountForCurrentDir(
                new DirectoryFilesAmountDTO(dirName, filesInCurrDir)
        );
        resetCurrentDirFilesAmount();
    }

    private void incrementFilesAmount() {
        allFilesAmount++;
        currentDirFilesAmount++;
    }

    private void saveAllFilesAmount() {
        resultFiles.setAllFilesAmount(allFilesAmount);
    }

    private void resetCurrentDirFilesAmount() {
        currentDirFilesAmount = 0;
    }

}
