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
            java.io.FileReader fileReader = new java.io.FileReader(getClass().getClassLoader().getResource(inputFilename).getFile());
            JSONObject inputFile = (JSONObject) jsonParser.parse(fileReader);
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
        String inputFilename = "input.txt";
        System.out.println(fileReader.readInputDataFromFile(inputFilename));
    }
}
