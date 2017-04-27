package main.java.dto;

import java.util.List;

public class ResultFilesDTO {
    private Integer allFilesAmount;
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

    public void setAllFilesAmount(Integer allFilesAmount) {
        this.allFilesAmount = allFilesAmount;
    }

    public void addFilesAmountForCurrentDir(DirectoryFilesAmountDTO fileCount) {
        filesAmountPerDirectoryBranch.add(fileCount);
    }
}
