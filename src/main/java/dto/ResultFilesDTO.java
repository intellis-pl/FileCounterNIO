package main.java.dto;

import java.util.List;

public class ResultFilesDTO {
    private Integer allFilesAmount;
    private Integer currentDirFilesAmount;
    private List<DirectoryFilesAmountDTO> filesAmountPerDirectoryBranch;

    public ResultFilesDTO(Integer allFilesAmount, List<DirectoryFilesAmountDTO> filesAmountPerDirectoryBranch) {
        this.allFilesAmount = allFilesAmount;
        this.filesAmountPerDirectoryBranch = filesAmountPerDirectoryBranch;
    }

    public List<DirectoryFilesAmountDTO> getFilesAmountPerDirectoryBranch() {
        return filesAmountPerDirectoryBranch;
    }

    public Integer getAllFilesAmount() {
        return allFilesAmount;
    }

    public Integer getCurrentDirFilesAmount() {
        return currentDirFilesAmount;
    }

    public void setCurrentDirFilesAmount(Integer currentDirFilesAmount) {
        this.currentDirFilesAmount = currentDirFilesAmount;
    }

    public void incAllFilesAmount() {
        this.allFilesAmount++;
    }

    public void incCurrentDirFilesAmount() {
        this.currentDirFilesAmount++;
    }

    public void addFilesAmountForCurrentDir(DirectoryFilesAmountDTO fileCount) {
        filesAmountPerDirectoryBranch.add(fileCount);
    }
}
