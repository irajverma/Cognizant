package com.cognizant.mockito.advanced;

/**
 * File service that depends on FileReader and FileWriter for Advanced Mockito Exercise 3.
 */
public class FileService {

    private final FileReader fileReader;
    private final FileWriter fileWriter;

    public FileService(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    public String processFile() {
        String content = fileReader.read();
        return "Processed " + content;
    }

    public void writeToFile(String content) {
        fileWriter.write(content);
    }
}
