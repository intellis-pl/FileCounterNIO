package main.java.dto;


public class DirectoryFilesAmountDTO {
    private String directoryName;
    private Integer fileAmount;

    public DirectoryFilesAmountDTO(String directoryName, Integer fileAmount) {
        this.directoryName = directoryName;
        this.fileAmount = fileAmount;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public Integer getFileAmount() {
        return fileAmount;
    }
}
