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

import static main.java.helpers.ResultFilesHelper.*;


public class ThreeWalker implements FileVisitor<Path> {
    private final static Logger LOGGER = Logger.getLogger(ThreeWalker.class);

    private ResultFilesDTO resultFiles;

    {
        resultFiles = new ResultFilesDTO(0, new ArrayList<>());
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        resultFiles = resetCurrentDirFilesAmount(resultFiles);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path currentFile, BasicFileAttributes attrs) throws IOException {
        if (isMatchFile(currentFile)) {
            resultFiles.incAllFilesAmount();
            resultFiles.incCurrentDirFilesAmount();
            return FileVisitResult.CONTINUE;
        }
        return FileVisitResult.SKIP_SUBTREE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        LOGGER.error(exc.getMessage());
        return FileVisitResult.TERMINATE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path currentFile, IOException exc) throws IOException {
        String dirName = currentFile.getFileName().toString();
        resultFiles.addFilesAmountForCurrentDir(
                new DirectoryFilesAmountDTO(dirName, resultFiles.getCurrentDirFilesAmount())
        );
        resultFiles = resetCurrentDirFilesAmount(resultFiles);
        return FileVisitResult.CONTINUE;
    }

    public ResultFilesDTO getResultFiles() {
        return resultFiles;
    }
}
