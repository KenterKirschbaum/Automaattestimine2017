package weatherController;

import consoleScanner.ConsoleScanner;
import currentWeather.CurrentWeatherReport;
import currentWeather.CurrentWeatherRepository;
import fileReader.FileReader;
import fileWriter.FileWriter;
import weatherForecast.ForecastReport;
import weatherForecast.ForecastRepository;
import weatherRequest.WeatherRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeatherController {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter 'C' for console scanner, 'F' for file reading.");
        String inputChoiceString = bufferedReader.readLine();

        FileWriter fileWriter = new FileWriter();

        List<Double> minTemperatures = new ArrayList<Double>();
        List<Double> maxTemperatures = new ArrayList<Double>();

        if (inputChoiceString.equals("F")) {
            FileReader fileReader = new FileReader();
            WeatherRequest weatherRequest = fileReader.readInputDataFromInputFile();
            
            ForecastRepository forecastRepository = new ForecastRepository();

            forecastRepository.responseJsonDataToForecastWeatherReport(weatherRequest);
            ForecastReport forecastReport = forecastRepository.responseJsonDataToForecastWeatherReport(weatherRequest);

            CurrentWeatherRepository currentWeatherRepository = new CurrentWeatherRepository();
            CurrentWeatherReport currentWeatherReport = currentWeatherRepository.responseJsonDataToCurrentWeatherReport(weatherRequest);

            double temperature = currentWeatherReport.getTemperature();
            double latitude = currentWeatherReport.getLatitude();
            double longitude = currentWeatherReport.getLongitude();
            String city = currentWeatherReport.getCity();

            double maxTempDayOne = forecastReport.getFirstDay().getMaxTemperature();
            double minTempDayOne = forecastReport.getFirstDay().getMinTemperature();

            double maxTempDayTwo = forecastReport.getSecondDay().getMaxTemperature();
            double minTempDayTwo = forecastReport.getSecondDay().getMinTemperature();

            double maxTempDayThree = forecastReport.getThirdDay().getMaxTemperature();
            double minTempDayThree = forecastReport.getThirdDay().getMinTemperature();

            //Add min tempretures
            minTemperatures.add(minTempDayOne);
            minTemperatures.add(minTempDayTwo);
            minTemperatures.add(minTempDayThree);

            //Add max tempretures
            maxTemperatures.add(maxTempDayOne);
            maxTemperatures.add(maxTempDayTwo);
            maxTemperatures.add(maxTempDayThree);

            double minTemperature = Collections.min(minTemperatures);
            double maxTemperature = Collections.max(maxTemperatures);

            fileWriter.writeDataToFile(city, temperature, longitude, maxTemperature, minTemperature, latitude);

            System.out.println("File writing was successful.");
            


        }

        if (inputChoiceString.equals("C")) {
            ConsoleScanner consoleScanner = new ConsoleScanner();
            WeatherRequest weatherRequestObject = consoleScanner.buildWeatherRequestFromConsoleInput();

            ForecastRepository forecastRepository = new ForecastRepository();

            forecastRepository.responseJsonDataToForecastWeatherReport(weatherRequestObject);
            ForecastReport forecastReport = forecastRepository.responseJsonDataToForecastWeatherReport(weatherRequestObject);

            CurrentWeatherRepository currentWeatherRepository = new CurrentWeatherRepository();
            CurrentWeatherReport currentWeatherReport = currentWeatherRepository.responseJsonDataToCurrentWeatherReport(weatherRequestObject);

            double temperature = currentWeatherReport.getTemperature();
            double latitude = currentWeatherReport.getLatitude();
            double longitude = currentWeatherReport.getLongitude();
            String city = currentWeatherReport.getCity();

            double maxTempDayOne = forecastReport.getFirstDay().getMaxTemperature();
            double minTempDayOne = forecastReport.getFirstDay().getMinTemperature();
            
            double maxTempDayTwo = forecastReport.getSecondDay().getMaxTemperature();
            double minTempDayTwo = forecastReport.getSecondDay().getMinTemperature();
            
            double maxTempDayThree = forecastReport.getThirdDay().getMaxTemperature();
            double minTempDayThree = forecastReport.getThirdDay().getMinTemperature();
            
            //Add min tempretures
            minTemperatures.add(minTempDayOne);
            minTemperatures.add(minTempDayTwo);
            minTemperatures.add(minTempDayThree);

            //Add max tempretures
            maxTemperatures.add(maxTempDayOne);
            maxTemperatures.add(maxTempDayTwo);
            maxTemperatures.add(maxTempDayThree);

            double minTemperature = Collections.min(minTemperatures);
            double maxTemperature = Collections.max(maxTemperatures);

            fileWriter.writeDataToFile(city, temperature, longitude, maxTemperature, minTemperature, latitude);

            System.out.println("File writing was successful.");

        }
    }
}
