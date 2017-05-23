package main.java.helpers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemporaryResultFilesManagerTest {
    private TemporaryResultFilesManager temporaryResultFiles;
    
    @Before
    public void init() {
        temporaryResultFiles = new TemporaryResultFilesManager();
    }

    @Test
    public void shouldInitTempResultMapAsDefaultForExampleDirectoryName() {
        //given
        Integer noDirValue = temporaryResultFiles.findAmountForDirectory("D");

        //when
        temporaryResultFiles.registerTempDirectory("D");

        //then
        assertNull(noDirValue);
        assertNotNull(temporaryResultFiles.findAmountForDirectory("D"));
    }

    @Test
    public void shouldClearTempResultMapForExampleDirectoryName() {
        //given
        temporaryResultFiles.registerTempDirectory("D");
        Integer dirValue = temporaryResultFiles.findAmountForDirectory("D");

        //when
        temporaryResultFiles.unregisterTempDirectory("D");

        //then
        assertNotNull(dirValue);
        assertNull(temporaryResultFiles.findAmountForDirectory("D"));
    }

    @Test
    public void shouldIncrementTempResultMapForExampleDirectoryName() {
        //given
        temporaryResultFiles.registerTempDirectory("D");
        Integer initValue = temporaryResultFiles.findAmountForDirectory("D");

        //when
        temporaryResultFiles.updateFilesAmountForParents();

        //then
        assertEquals(0, initValue.intValue());
        assertEquals(1, temporaryResultFiles.findAmountForDirectory("D").intValue());
    }
}