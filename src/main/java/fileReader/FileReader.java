package fileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import weatherRequest.WeatherRequest;

public class FileReader {

    public WeatherRequest readInputDataFromFile (String inputFilename)   {
        JSONParser jsonParser = new JSONParser();
        WeatherRequest weatherRequest = null;
        try {
            java.io.FileReader fileReader = new java.io.FileReader(inputFilename);
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
}
