package weatherForecast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import weatherRequest.WeatherRequest;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

public class ForecastRepository {

    public static final String APIKey = "4ce9b7fca2966afda1256db99427f770";

    public static String buildForecastWeatherURL(WeatherRequest weatherRequest){
        UriBuilder builder = UriBuilder
                .fromPath("http://api.openweathermap.org")
                .path("/data/2.5/forecast")
                .queryParam("q", weatherRequest.getCity() + "," + weatherRequest.getCountry())
                .queryParam("APPID", APIKey)
                .queryParam("units", weatherRequest.getMetricFormat());

        URL urlForForecastWeather = null;

        try{
            urlForForecastWeather = builder.build().toURL();
        }
        catch (MalformedURLException urlException) {
            urlException.printStackTrace();
        }
        return urlForForecastWeather.toString();
    }

    public JSONObject getForecastWeatherReport(WeatherRequest weatherRequest){
        String connectionUrl = buildForecastWeatherURL(weatherRequest);
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

    public ForecastReport responseJsonDataToForecastWeatherReport (WeatherRequest weatherRequest){

        JSONObject weatherReportJsonFormat = getForecastWeatherReport(weatherRequest);
        JSONObject cityObject = (JSONObject) weatherReportJsonFormat.get("city");
        JSONObject coordinatesObject = (JSONObject) cityObject.get("coord");
        double latitudeValue = (double) coordinatesObject.get("lat");
        double longitudeValue = (double) coordinatesObject.get("lon");
        String cityName = (String) cityObject.get("name");
        String countryCode = (String) cityObject.get("country");
        OneDayWeatherReport firstDay = getOneDayReport(weatherReportJsonFormat, 1);
        OneDayWeatherReport secondDay = getOneDayReport(weatherReportJsonFormat, 2);
        OneDayWeatherReport thirdDay = getOneDayReport(weatherReportJsonFormat, 3);

        ForecastReport forecastReport = new ForecastReport(cityName, countryCode, longitudeValue, latitudeValue, firstDay, secondDay, thirdDay);
        return forecastReport;
    }

    public OneDayWeatherReport getOneDayReport(JSONObject forecastObject, int day){
    	int dayOfMonthToday = (new Timestamp(System.currentTimeMillis())).toLocalDateTime().getDayOfMonth();
        JSONArray forecast = (JSONArray) forecastObject.get("list");
        double latestMaximumTemperature = Integer.MIN_VALUE;
        double latestMinimumTemperature = Integer.MAX_VALUE;
        for (int i = 0; i < forecast.size(); i++) {
            JSONObject singleForecast = (JSONObject) forecast.get(i);
            Timestamp timestamp = new Timestamp((Long) singleForecast.get("dt") * 1000);
            JSONObject main = (JSONObject) singleForecast.get("main");
            Object minTempObj = main.get("temp_min");
            Object maxTempObj = main.get("temp_max");
            double minTemp = new Double(minTempObj.toString());
            double maxTemp = new Double(maxTempObj.toString());
            int numberOfDaysFromToday = timestamp.toLocalDateTime().getDayOfMonth() - dayOfMonthToday;
            if (numberOfDaysFromToday == day) {
                if (minTemp < latestMinimumTemperature) {
                	latestMinimumTemperature = minTemp;
                }
                if (maxTemp > latestMaximumTemperature) {
                	latestMaximumTemperature = maxTemp;
                }
            }
        }
        OneDayWeatherReport oneDayReport = new OneDayWeatherReport(latestMaximumTemperature, latestMinimumTemperature);
        return oneDayReport;
    }
}
