package com.cognizant.mockito.advanced;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Advanced Mockito Exercise 3: Mocking File I/O.
 */
class FileServiceTest {

    @Test
    @DisplayName("Test service with mock file reader and writer")
    void testServiceWithMockFileIO() {
        // Create mock file reader and writer
        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);

        // Stub the file reader
        when(mockFileReader.read()).thenReturn("Mock File Content");

        // Create the service
        FileService fileService = new FileService(mockFileReader, mockFileWriter);
        String result = fileService.processFile();

        // Verify
        assertEquals("Processed Mock File Content", result);
        verify(mockFileReader).read();
    }

    @Test
    @DisplayName("Test writing to file with mock writer")
    void testWriteToFile() {
        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);

        FileService fileService = new FileService(mockFileReader, mockFileWriter);
        fileService.writeToFile("test content");

        // Verify the writer was called with the correct content
        verify(mockFileWriter).write("test content");
    }

    @Test
    @DisplayName("Test file reader returns empty content")
    void testEmptyFileContent() {
        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenReturn("");

        FileService fileService = new FileService(mockFileReader, mockFileWriter);
        String result = fileService.processFile();

        assertEquals("Processed ", result);
    }
}
