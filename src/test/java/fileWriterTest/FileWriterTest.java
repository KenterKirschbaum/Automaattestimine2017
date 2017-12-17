package fileWriterTest;

import org.junit.Test;

import fileWriter.FileWriter;

public class FileWriterTest {
	
	private FileWriter fileWriter;
	
	@Test
    public void doesWriterWriteToFile() {
	FileWriter filewriter = new FileWriter();
	filewriter.writeData("Test", 111.2, 12.4, 1.1, 1.2, 1.3);
	}
}