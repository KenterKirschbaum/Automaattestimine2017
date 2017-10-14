package currentWeather;

import httpUtilities.HttpUtilities;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import weatherRequest.WeatherRequest;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class CurrentWeatherRepository {

    public static final String APIKey = "4ce9b7fca2966afda1256db99427f770";

    public static String buildCurrentWeatherURL(WeatherRequest request){
        UriBuilder builder = UriBuilder
                .fromPath("http://api.openweathermap.org")
                .path("/data/2.5/weather")
                .queryParam("q", request.getCity())
                .queryParam("APPID", APIKey)
                .queryParam("units", request.getMetricFormat());

        URL urlForCurrentWeather = null;

        try{
            urlForCurrentWeather = builder.build().toURL();
        }
        catch (MalformedURLException urlException) {
            urlException.printStackTrace();
        }
        return urlForCurrentWeather.toString();
    }

    public JSONObject getCurrentWeatherReport(WeatherRequest weatherRequest) throws IOException {
        HttpURLConnection request = HttpUtilities.makeHttpURLRequest(buildCurrentWeatherURL(weatherRequest));
        request.connect();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader((InputStream) request.getContent()));
        }   catch (IOException  | ParseException parseException) {
            parseException.printStackTrace();
        }
        request.disconnect();
        return jsonObject;
    }

    public CurrentWeatherReport responseJsonDataToCurrentWeatherReport (WeatherRequest weatherRequest) throws IOException   {

        JSONObject weatherReportJsonFormat = getCurrentWeatherReport(weatherRequest);
        JSONObject systemInformation = (JSONObject) weatherReportJsonFormat.get("sys");
        JSONObject mainInformation = (JSONObject) weatherReportJsonFormat.get("main");
        JSONObject coordinates = (JSONObject) weatherReportJsonFormat.get("coord");

        String cityName = (String) weatherReportJsonFormat.get("name");
        String countryName = (String) systemInformation.get("country");
        double temprerature = (double) mainInformation.get("temp");
        double longitudeValue = (double) coordinates.get("lon");
        double latitudeValue = (double) coordinates.get("lat");

        CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(cityName,countryName,temprerature,
                longitudeValue,latitudeValue);
        return currentWeatherReport;
    }


}