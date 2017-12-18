package fileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import weatherRequest.WeatherRequest;

import java.util.ArrayList;

public class FileReader {

    ArrayList<WeatherRequest> weatherRequestList = new ArrayList<WeatherRequest>();
    WeatherRequest weatherRequest = new WeatherRequest(null, null, null);

    public  WeatherRequest readInputDataFromInputFile ()   {

        JSONParser jsonParser = new JSONParser();
        
        try {
            Object object = jsonParser.parse(new java.io.FileReader("C:/Users/kente_000/Desktop/Automaattestimine2017/src/main/resources/inputFile.txt"));

            JSONObject jsonObject = (JSONObject) object;
            String city = (String) jsonObject.get("cityName");
            String country = (String) jsonObject.get("countryCode");
            String format = (String) jsonObject.get("metricUnits");

            weatherRequest.setCity(city);
            weatherRequest.setCountry(country);
            weatherRequest.setMetricFormat(format);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return weatherRequest;
    }



}