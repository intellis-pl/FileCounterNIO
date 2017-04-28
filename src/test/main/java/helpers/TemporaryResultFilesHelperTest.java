package main.java.helpers;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TemporaryResultFilesHelperTest {

    @Test
    public void shouldInitTempResultMapAsDefaultForExampleDirectoryName() {
        //given
        Map<String, Integer> tempResultFilesMap = new LinkedHashMap<>();
        Boolean hasNotKey = tempResultFilesMap.containsKey("D");

        //when
        TemporaryResultFilesHelper.registerTempDirectory(tempResultFilesMap, "D");

        //then
        assertFalse(hasNotKey);
        assertTrue(tempResultFilesMap.containsKey("D"));
    }

    @Test
    public void shouldClearTempResultMapForExampleDirectoryName() {
        //given
        Map<String, Integer> tempResultFilesMap = new LinkedHashMap<>();
        TemporaryResultFilesHelper.registerTempDirectory(tempResultFilesMap, "D");
        Boolean hasKey = tempResultFilesMap.containsKey("D");

        //when
        TemporaryResultFilesHelper.unregisterTempDirectory(tempResultFilesMap, "D");

        //then
        assertTrue(hasKey);
        assertFalse(tempResultFilesMap.containsKey("D"));
    }

    @Test
    public void shouldIncrementTempResultMapForExampleDirectoryName() {
        //given
        Map<String, Integer> tempResultFilesMap = new LinkedHashMap<>();
        TemporaryResultFilesHelper.registerTempDirectory(tempResultFilesMap, "D");
        Integer initValue = tempResultFilesMap.get("D");

        //when
        TemporaryResultFilesHelper.updateFilesAmountForParents(tempResultFilesMap);

        //then
        assertEquals(0, initValue.intValue());
        assertEquals(1, tempResultFilesMap.get("D").intValue());
    }
}