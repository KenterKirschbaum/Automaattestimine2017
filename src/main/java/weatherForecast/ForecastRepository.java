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
        int currentDayOfMonth = (new Timestamp(System.currentTimeMillis())).toLocalDateTime().getDayOfMonth();
        JSONArray forecast = (JSONArray) forecastObject.get("list");
        double latestMinimumTemperature = Integer.MAX_VALUE;
        double latestMaximumTemperature = Integer.MIN_VALUE;
        for (int i = 0; i < forecast.size(); i++)   {
            JSONObject singleForecastObject = (JSONObject) forecast.get(i);
            Timestamp timestamp = new Timestamp((Long) singleForecastObject.get("dt") * 1000);
            JSONObject mainObject = (JSONObject) singleForecastObject.get("main");
            Object minimumTemperatureObject = mainObject.get("temp_min");
            Object maximumTemperatureObject = mainObject.get("temp_max");
            double minimumTemperatureValue = new Double(minimumTemperatureObject.toString());
            double maximumTemperatureValue = new Double(maximumTemperatureObject.toString());
            int daysFromToday = timestamp.toLocalDateTime().getDayOfMonth() - currentDayOfMonth;
            if (daysFromToday == day)   {
                if (minimumTemperatureValue < latestMinimumTemperature) {
                    latestMinimumTemperature = minimumTemperatureValue;
                }
                if (maximumTemperatureValue < latestMaximumTemperature) {
                    latestMaximumTemperature = maximumTemperatureValue;
                }
            }
        }
        OneDayWeatherReport oneDayReport = new OneDayWeatherReport(latestMaximumTemperature,latestMinimumTemperature);
        return oneDayReport;
    }
/*
    public void writeJsonResponseDataToFile(JSONObject jsonObject){
        FileWriter fileWriter = new FileWriter();
        fileWriter.writeJsonDataToFile(jsonObject);
    }

    public WeatherRequest getWeatherRequestFromConsole() {
        ConsoleScanner consoleScanner = new ConsoleScanner();
        return consoleScanner.buildWeatherRequestFromConsoleInput();
    }

    public WeatherRequest getWeatherRequestDataFromFile(String filename){
        FileReader fileReader = new FileReader();
        return fileReader.readInputDataFromFile(filename);
    }*/
}
