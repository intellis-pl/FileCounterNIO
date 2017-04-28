package main.java.search;

import main.java.dto.DirectoryFilesAmountDTO;
import main.java.dto.ResultFilesDTO;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ThreeFileCounterTest {
    private final static String PATH = "./search_dir/";
    private ThreeFileCounter searcher;

    @Before
    public void init() {
        searcher = new ThreeFileCounter();
    }

    @Test
    public void shouldFindTwelveFilesForEntireBranchOf_A_Directory() throws IOException {
        //given
        Integer expectedResult = 12;
        Path searchFile = Paths.get(PATH + "A");

        //when
        Files.walkFileTree(searchFile, searcher);
        ResultFilesDTO resultFiles = searcher.getResultFiles();
        Integer resultFromList = findFilesAmountForDirectory("A", resultFiles.getFilesAmountPerDirectoryBranch());

        //then
        assertEquals(expectedResult, resultFiles.getAllFilesAmount());
        assertEquals(expectedResult, resultFromList);
    }

    @Test
    public void shouldFindSevenFilesForDirectory_D() throws IOException {
        //given
        Integer expectedResult = 7;
        Path searchFile = Paths.get(PATH + "A/D");

        //when
        Files.walkFileTree(searchFile, searcher);
        ResultFilesDTO resultFiles = searcher.getResultFiles();
        Integer resultFromList = findFilesAmountForDirectory("D", resultFiles.getFilesAmountPerDirectoryBranch());

        //then
        assertEquals(expectedResult, resultFiles.getAllFilesAmount());
        assertEquals(expectedResult, resultFromList);
    }

    @Test
    public void shouldFindThreeFilesForDirectory_C() throws IOException {
        //given
        Integer expectedResult = 3;
        Path searchFile = Paths.get(PATH + "A/C");

        //when
        Files.walkFileTree(searchFile, searcher);
        ResultFilesDTO resultFiles = searcher.getResultFiles();
        Integer resultFromList = findFilesAmountForDirectory("C", resultFiles.getFilesAmountPerDirectoryBranch());

        //then
        assertEquals(expectedResult, resultFiles.getAllFilesAmount());
        assertEquals(expectedResult, resultFromList);
    }

    @Test
    public void shouldFindTwoFilesForDirectory_B() throws IOException {
        //given
        Integer expectedResult = 2;
        Path searchFile = Paths.get(PATH + "A/B");

        //when
        Files.walkFileTree(searchFile, searcher);
        ResultFilesDTO resultFiles = searcher.getResultFiles();
        Integer resultFromList = findFilesAmountForDirectory("B", resultFiles.getFilesAmountPerDirectoryBranch());

        //then
        assertEquals(expectedResult, resultFiles.getAllFilesAmount());
        assertEquals(expectedResult, resultFromList);
    }

    @Test
    public void shouldFindFourFilesForDirectory_E() throws IOException {
        //given
        Integer expectedResult = 4;
        Path searchFile = Paths.get(PATH + "A/D/E");

        //when
        Files.walkFileTree(searchFile, searcher);
        ResultFilesDTO resultFiles = searcher.getResultFiles();
        Integer resultFromList = findFilesAmountForDirectory("E", resultFiles.getFilesAmountPerDirectoryBranch());

        //then
        assertEquals(expectedResult, resultFiles.getAllFilesAmount());
        assertEquals(expectedResult, resultFromList);
    }

    @Test
    public void shouldFindOneFileForDirectory_F() throws IOException {
        //given
        Integer expectedResult = 1;
        Path searchFile = Paths.get(PATH + "A/D/F");

        //when
        Files.walkFileTree(searchFile, searcher);
        ResultFilesDTO resultFiles = searcher.getResultFiles();
        Integer resultFromList = findFilesAmountForDirectory("F", resultFiles.getFilesAmountPerDirectoryBranch());

        //then
        assertEquals(expectedResult, resultFiles.getAllFilesAmount());
        assertEquals(expectedResult, resultFromList);
    }

    @Test
    public void shouldFindOneFileForDirectory_I() throws IOException {
        //given
        Integer expectedResult = 1;
        Path searchFile = Paths.get(PATH + "A/D/G/I");

        //when
        Files.walkFileTree(searchFile, searcher);
        ResultFilesDTO resultFiles = searcher.getResultFiles();
        Integer resultFromList = findFilesAmountForDirectory("I", resultFiles.getFilesAmountPerDirectoryBranch());

        //then
        assertEquals(expectedResult, resultFiles.getAllFilesAmount());
        assertEquals(expectedResult, resultFromList);
    }

    @Test
    public void shouldFindOneFileForEntireBranchOf_G_Directory() throws IOException {
        //given
        Integer expectedResult = 1;
        Path searchFile = Paths.get(PATH + "A/D/G");

        //when
        Files.walkFileTree(searchFile, searcher);
        ResultFilesDTO resultFiles = searcher.getResultFiles();
        Integer resultFromList = findFilesAmountForDirectory("G", resultFiles.getFilesAmountPerDirectoryBranch());

        //then
        assertEquals(expectedResult, resultFiles.getAllFilesAmount());
        assertEquals(expectedResult, resultFromList);
    }

    private Integer findFilesAmountForDirectory(String dirName, List<DirectoryFilesAmountDTO> resultFiles) {
        for(DirectoryFilesAmountDTO dto : resultFiles) {
            if(dto.getDirectoryName().equals(dirName)) {
             return dto.getFileAmount();
            }
        }
        return 0;
    }

}