package main.java.search;

import main.java.dto.DirectoryFilesAmountDTO;
import main.java.dto.ResultFilesDTO;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static main.java.helpers.ResultFilesHelper.isMatchFile;
import static main.java.helpers.ResultFilesHelper.resetCurrentDirFilesAmount;


public class ThreeWalker implements FileVisitor<Path> {
    private final static Logger LOGGER = Logger.getLogger(ThreeWalker.class);

    private ResultFilesDTO resultFiles;
    private Map<String, Integer> resultFilesMap;

    {
        resultFiles = new ResultFilesDTO(0, new ArrayList<>());
        resultFilesMap = new HashMap<>();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        resultFiles = resetCurrentDirFilesAmount(resultFiles);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path currentFile, BasicFileAttributes attrs) throws IOException {
        if (isMatchFile(currentFile)) {
            String dirName = currentFile.getParent().getFileName().toString();
            incrementFilesAmount(dirName);
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
        String dirName = currentFile.getFileName().toString();
        saveFilesAmountForCurrentDirectory(dirName);
        return FileVisitResult.CONTINUE;
    }

    public ResultFilesDTO getResultFiles() {
        return resultFiles;
    }

    private void incrementFilesAmount(String dirName) {
        resultFiles.incAllFilesAmount();
        resultFilesMap.put(
                dirName, resultFiles.incCurrentDirFilesAmount());
    }

    private void saveFilesAmountForCurrentDirectory(String dirName) {
        Integer filesInCurrDir = findFilesAmountInCurrentDir(dirName);
        resultFiles.addFilesAmountForCurrentDir(
                new DirectoryFilesAmountDTO(dirName, filesInCurrDir)
        );
        resultFiles = resetCurrentDirFilesAmount(resultFiles);
    }

    private Integer findFilesAmountInCurrentDir(String dirName) {
        if(resultFilesMap.get(dirName) != null) {
            return resultFilesMap.get(dirName);
        }
        return resultFiles.getCurrentDirFilesAmount();
    }
}
