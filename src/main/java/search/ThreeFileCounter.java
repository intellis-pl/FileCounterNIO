package main.java.search;

import main.java.dto.DirectoryFilesAmountDTO;
import main.java.dto.ResultFilesDTO;
import main.java.helpers.FileMatcher;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import static main.java.helpers.TemporaryResultFilesHelper.*;


public class ThreeFileCounter implements FileVisitor<Path> {
    private final static Logger LOGGER = Logger.getLogger(ThreeFileCounter.class);

    private Integer allFilesAmount;
    private Integer currentDirFilesAmount;
    private ResultFilesDTO resultFiles;
    private Map<String, Integer> tempResultFilesMap;

    {
        resultFiles = new ResultFilesDTO(0, new LinkedList<>());
        tempResultFilesMap = new LinkedHashMap<>();
        allFilesAmount = 0;
        currentDirFilesAmount = 0;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        String dirName = findCatalogName(dir);
        tempResultFilesMap = initTempDirectory(tempResultFilesMap, dirName);
        resetCurrentDirFilesAmount();
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path currentFile, BasicFileAttributes attrs) throws IOException {
        if (FileMatcher.isFileMatch(currentFile)) {
            incrementFilesAmount();
            saveAllFilesAmount();
            tempResultFilesMap = updateFilesAmountForParents(tempResultFilesMap);
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
        String dirName = findCatalogName(currentFile);
        saveFilesAmountForCurrentDirectory(dirName);
        tempResultFilesMap = clearTempDirectory(tempResultFilesMap, dirName);
        return FileVisitResult.CONTINUE;
    }

    public ResultFilesDTO getResultFiles() {
        return resultFiles;
    }

    private String findCatalogName(Path currentFile) {
        return currentFile.getFileName().toString();
    }

    private void saveFilesAmountForCurrentDirectory(String dirName) {
        Integer filesInCurrDir = tempResultFilesMap.get(dirName);
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
