package fileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import weatherRequest.WeatherRequest;

import java.io.IOException;

public class FileReader {

    public WeatherRequest readInputDataFromFile (String inputFilename)   {
        JSONParser jsonParser = new JSONParser();
        WeatherRequest weatherRequest = null;
        try {
            java.io.FileReader fileReader = new java.io.FileReader("C:/Users/kente_000/IdeaProjects/Automaattestimine2017/src/main/resources/inputFile.txt");            JSONObject inputFile = (JSONObject) jsonParser.parse(fileReader);
            String city = (String) inputFile.get("city");
            String countryCode = (String) inputFile.get("countryCode");
            String metricUnits = (String) inputFile.get("units");
            weatherRequest = new WeatherRequest(city, countryCode, metricUnits);
         } catch (IOException | ParseException exception)   {
            exception.printStackTrace();
        }
        return weatherRequest;
    }

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        System.out.println(fileReader.readInputDataFromFile("C:/Users/kente_000/IdeaProjects/Automaattestimine2017/src/main/resources/inputFile.txt"));
    }
}
