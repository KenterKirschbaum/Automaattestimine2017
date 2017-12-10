package fileWriter;

import org.json.simple.JSONObject;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileWriter {

    public static void writeJsonDataToFile(JSONObject jsonObject, String outputFile)   {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(outputFile);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
        } catch (FileNotFoundException exception)   {
            System.out.println(exception);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
