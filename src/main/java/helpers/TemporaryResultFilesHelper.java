package main.java.helpers;


import java.util.Map;

public class TemporaryResultFilesHelper {

    public static Map<String, Integer> initTempFilesMapForCurrentDir(Map<String, Integer> tempResultFilesMap, String dirName) {
        tempResultFilesMap.put(dirName, 0);
        return tempResultFilesMap;
    }

    public static Map<String, Integer> clearTempResultFilesMap(Map<String, Integer> tempResultFilesMap, String dirName) {
        tempResultFilesMap.remove(dirName);
        return tempResultFilesMap;
    }

    public static Map<String, Integer> updateFilesAmountForParents(Map<String, Integer> tempResultFilesMap) {
        for (Map.Entry<String, Integer> entry : tempResultFilesMap.entrySet()) {
            entry.setValue(entry.getValue() + 1);
        }
        return tempResultFilesMap;
    }
}
