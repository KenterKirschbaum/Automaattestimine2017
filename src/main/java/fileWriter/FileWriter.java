package fileWriter;

import org.json.simple.JSONObject;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileWriter {

    public static void writeJsonDataToFile(JSONObject jsonObject)   {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter("C:/Users/kente_000/IdeaProjects/Automaattestimine2017/src/main/resources/outputFile.txt");
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
        } catch (FileNotFoundException exception)   {
            System.out.println(exception);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args)  {
        JSONObject object = new JSONObject();
        object.put("cityName", "Tallinn");
        object.put("countryCode", "EE");
        object.put("metricUnits", "metric");
        FileWriter.writeJsonDataToFile(object);
    }
}
