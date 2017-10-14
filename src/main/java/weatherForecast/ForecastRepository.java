package weatherForecast;

import httpUtilities.HttpUtilities;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import weatherRequest.WeatherRequest;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ForecastRepository {

    public static final String APIKey = "4ce9b7fca2966afda1256db99427f770";

    public static String buildForecastWeatherURL(WeatherRequest request){
        UriBuilder builder = UriBuilder
                .fromPath("http://api.openweathermap.org")
                .path("/data/2.5/weather")
                .queryParam("q", request.getCity())
                .queryParam("APPID", APIKey)
                .queryParam("units", request.getMetricFormat());

        URL urlForForecastWeather = null;

        try{
            urlForForecastWeather = builder.build().toURL();
        }
        catch (MalformedURLException urlException) {
            urlException.printStackTrace();
        }
        return urlForForecastWeather.toString();
    }

    public JSONObject getForecastWeatherReport(WeatherRequest weatherRequest) throws IOException {
        HttpURLConnection request = HttpUtilities.makeHttpURLRequest(buildForecastWeatherURL(weatherRequest));
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

    public ForecastReport responseJsonDataToForecastWeatherReport (WeatherRequest weatherRequest) throws  IOException   {
        JSONObject weatherReportJsonFormat = getForecastWeatherReport(weatherRequest);
        JSONObject city = (JSONObject) weatherReportJsonFormat.get("city");
        JSONObject coordinates = (JSONObject) city.get("coord");
        double latitudeValue = (double) coordinates.get("lat");
        double longitudeValue = (double) coordinates.get("lon");
        String cityName = (String) city.get("name");
        String countryName = (String) city.get("county");
        ForecastReportForOneDay firstDay = getOneDayReport(weatherReportJsonFormat, 1);
        ForecastReportForOneDay secondDay = getOneDayReport(weatherReportJsonFormat, 2);
        ForecastReportForOneDay thirdDay = getOneDayReport(weatherReportJsonFormat, 3);

        ForecastReport forecastReport = new ForecastReport(cityName, countryName, longitudeValue, latitudeValue, firstDay, secondDay, thirdDay);
        return forecastReport
    }

    public ForecastReportForOneDay getOneDayReport(JSONObject forecast, int day){ //pooleli

    }
}
