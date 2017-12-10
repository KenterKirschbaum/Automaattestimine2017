package fileReader;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class FileReader {

    public JSONArray readInputFromFile(String filename) {
        JSONParser parser = new JSONParser();
        JSONArray fileContent = null;

        try {
            java.io.FileReader fileReader = new java.io.FileReader("C:/Users/kente_000/IdeaProjects/Automaattestimine2017/src/main/resources/inputFile.txt");
            fileContent = (JSONArray) parser.parse(fileReader);
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }

        return fileContent;
    }

}
