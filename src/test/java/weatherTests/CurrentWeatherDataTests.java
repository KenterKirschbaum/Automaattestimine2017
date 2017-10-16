package weatherTests;


import currentWeather.CurrentWeatherReport;
import currentWeather.CurrentWeatherRepository;
import org.junit.Before;
import org.junit.Test;
import weatherRequest.WeatherRequest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class CurrentWeatherDataTests {
    private WeatherRequest weatherRequest;
    private CurrentWeatherReport currentWeatherReport;
    private CurrentWeatherRepository currentWeatherRepository;

    @Before
    public void initialObjectsData() throws IOException {
        weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        currentWeatherRepository = new CurrentWeatherRepository();
        currentWeatherReport = currentWeatherRepository.responseJsonDataToCurrentWeatherReport(weatherRequest);
    }

    @Test
    public void testIfResponseHasCoordinates() {
        assertNotEquals(null, currentWeatherReport.getLatitude());
        assertNotEquals(null, currentWeatherReport.getLongitude());
    }

    @Test
    public void testIfResponseCountryCodeMatchesRequestCountryCode() {
        assertEquals(weatherRequest.getCountry(), currentWeatherReport.getCountry());
    }

    @Test
    public void testIfResponseCityValueMatchRequestedCityName() {
        assertEquals(weatherRequest.getCity(),currentWeatherReport.getCity());
    }

    @Test
    public void testIfResponseHasTemperatureValue() {
        assertNotEquals(null, currentWeatherReport.getTemperature());
    }


}
