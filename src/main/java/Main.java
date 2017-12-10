import org.json.simple.JSONObject;

import fileReader.FileReader;
import fileWriter.FileWriter;

public class Main {

    public static void main(String[] args)  {
        FileReader fileReader = new FileReader();
        JSONObject object = new JSONObject();
        System.out.println(fileReader.readInputDataFromFile("inputFile.txt"));
        object.put("cityName", "Tallinn");
        object.put("countryCode", "EE");
        object.put("metricUnits", "metric");
        FileWriter.writeJsonDataToFile(object, "outputFile.txt");
    }
}
