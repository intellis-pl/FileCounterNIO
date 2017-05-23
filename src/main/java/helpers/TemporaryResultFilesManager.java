package main.java.helpers;


import java.util.LinkedHashMap;
import java.util.Map;

public class TemporaryResultFilesManager {
    private Map<String, Integer> tempResultFilesMap;

    {
        tempResultFilesMap = new LinkedHashMap<>();
    }

    public void registerTempDirectory(String dirName) {
        tempResultFilesMap.put(dirName, 0);
    }

    public void unregisterTempDirectory(String dirName) {
        tempResultFilesMap.remove(dirName);
    }

    public void updateFilesAmountForParents() {
        for (Map.Entry<String, Integer> entry : tempResultFilesMap.entrySet()) {
            entry.setValue(entry.getValue() + 1);
        }
    }

    public Integer findAmountForDirectory(String dirName) {
        return tempResultFilesMap.get(dirName);
    }
}
