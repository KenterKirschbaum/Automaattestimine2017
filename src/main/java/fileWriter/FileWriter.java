package fileWriter;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {

    public void writeJsonDataToFile(JSONObject jsonObject, String outoutFilename)   {
        try {
            Files.write(Paths.get(getClass().getClassLoader().getResource(outoutFilename).getPath()), jsonObject.toString().getBytes(), StandardOpenOption.WRITE);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args)  {
        JSONObject object = new JSONObject();
        object.put(""); //SIIN HETKEL ERROR, PUUDULIK
        String outoutFilename = "outputFile.txt";
        FileWriter fileWriter = new FileWriter();
        fileWriter.writeJsonDataToFile(object,outoutFilename);
    }
}
