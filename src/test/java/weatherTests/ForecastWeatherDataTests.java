package weatherTests;


import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import weatherForecast.ForecastReport;
import weatherForecast.ForecastRepository;
import weatherRequest.WeatherRequest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ForecastWeatherDataTests   {
    private WeatherRequest weatherRequest;
    private ForecastRepository forecastRepository;
    private ForecastReport forecastReport;


    @Before
    public void initObjects() throws IOException, ParseException {
        weatherRequest = new WeatherRequest("Tallinn","EE", "metric");
        forecastRepository = new ForecastRepository();
        forecastReport = forecastRepository.responseJsonDataToForecastWeatherReport(weatherRequest);
    }

    @Test
    public void testIfResponseCityMatchesRequestCity() {
        assertEquals(weatherRequest.getCity(), forecastReport.getCity());
    }

    @Test
    public void testIfResponseCountryMatchesRequeestCountry() {
        assertEquals(weatherRequest.getCountry(), forecastReport.getCountry());
    }

    @Test
    public void testIfResponseContainsCoordinates() {
        assertNotEquals(null, forecastReport.getLatitude());
        assertNotEquals(null, forecastReport.getLongitude());
    }

    @Test
    public void testIfResponseContainsThreeDayForecast() {
        assertNotEquals(null, forecastReport.getFirstDay());
        assertNotEquals(null, forecastReport.getSecondDay());
        assertNotEquals(null, forecastReport.getThirdDay());
    }

    @Test
    public void testIfResponseForecastDayOneContainsMaxTemp() {
        assertNotEquals(null, forecastReport.getFirstDay().getMaxTemperature());
    }

    @Test
    public void testIfResponseForecastDayOneContainsMinTemp() {
        assertNotEquals(null, forecastReport.getFirstDay().getMinTemperature());
    }

    @Test
    public void testIfResponseForecastDayTwoContainsMaxTemp() {
        assertNotEquals(null, forecastReport.getSecondDay().getMaxTemperature());
    }
    @Test
    public void testIfResponseForecastDayTwoContainsMinTemp() {
        assertNotEquals(null, forecastReport.getSecondDay().getMinTemperature());
    }

    @Test
    public void testIfResponseForecastDayThreeContainsMaxTemp() {
        assertNotEquals(null, forecastReport.getThirdDay().getMaxTemperature());
    }
    @Test
    public void testIfResponseForecastDayThreeContainsMinTemp() {
        assertNotEquals(null, forecastReport.getThirdDay().getMinTemperature());
    }

}
