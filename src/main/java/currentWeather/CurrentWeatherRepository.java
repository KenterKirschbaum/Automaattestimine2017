package currentWeather;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import weatherRequest.WeatherRequest;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class CurrentWeatherRepository {

    public static final String APIKey = "4ce9b7fca2966afda1256db99427f770";

    public static String buildCurrentWeatherURL(WeatherRequest weatherRequest){
        UriBuilder builder = UriBuilder
                .fromPath("http://api.openweathermap.org")
                .path("/data/2.5/weather")
                .queryParam("q", weatherRequest.getCity())
                .queryParam("APPID", APIKey)
                .queryParam("units", weatherRequest.getMetricFormat());

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
        String connectionUrl = buildCurrentWeatherURL(weatherRequest);
        org.apache.http.client.HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGetrequest = new HttpGet(connectionUrl);
        HttpResponse httpResponse = null;
        try {
            httpResponse = client.execute(httpGetrequest);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }
        return jsonObject;
    }

    public CurrentWeatherReport responseJsonDataToCurrentWeatherReport (WeatherRequest weatherRequest) throws IOException   {

        JSONObject weatherReportJsonFormat = getCurrentWeatherReport(weatherRequest);
        JSONObject systemInformation = (JSONObject) weatherReportJsonFormat.get("sys");
        JSONObject mainInformation = (JSONObject) weatherReportJsonFormat.get("main");
        JSONObject coordinates = (JSONObject) weatherReportJsonFormat.get("coord");

        String cityName = (String) weatherReportJsonFormat.get("name");
        String countryName = (String) systemInformation.get("country");
        long temprerature = (long) mainInformation.get("temp");
        double longitudeValue = (double) coordinates.get("lon");
        double latitudeValue = (double) coordinates.get("lat");

        CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(cityName,countryName,temprerature,
                longitudeValue,latitudeValue);
        return currentWeatherReport;
    }


}
