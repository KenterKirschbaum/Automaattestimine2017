package fileWriterTest;

import org.junit.Test;

import fileWriter.FileWriter;

public class FileWriterTest {
	
	private FileWriter fileWriter;
	
	@Test
    public void TestIfWriterWritesToFile() {
	FileWriter filewriter = new FileWriter();
	filewriter.writeDataToFile("Test", 111.2, 12.4, 1.1, 1.2, 1.3);
	}
}