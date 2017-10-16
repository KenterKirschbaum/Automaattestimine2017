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
    public void testIfResponseCityEqualsRequestCity() {
        assertEquals(weatherRequest.getCity(), forecastReport.getCity());
    }

    @Test
    public void testIfResponseCountryCodeEqualsRequeestCountryCode() {
        assertEquals(weatherRequest.getCountry(), forecastReport.getCountry());
    }

    @Test
    public void testIfResponseHasCoordinates() {
        assertNotEquals(null, forecastReport.getLatitude());
        assertNotEquals(null, forecastReport.getLongitude());
    }

    @Test
    public void testIfResponseHasThreeDayForecast() {
        assertNotEquals(null, forecastReport.getFirstDay());
        assertNotEquals(null, forecastReport.getSecondDay());
        assertNotEquals(null, forecastReport.getThirdDay());
    }

    @Test
    public void testIfResponseForecastDayOneHasMaxTemp() {
        assertNotEquals(null, forecastReport.getFirstDay().getMaxTemperature());
    }

    @Test
    public void testIfResponseForecastDayOneHasMinTemp() {
        assertNotEquals(null, forecastReport.getFirstDay().getMinTemperature());
    }

    @Test
    public void testIfResponseForecastDayTwoHasMaxTemp() {
        assertNotEquals(null, forecastReport.getSecondDay().getMaxTemperature());
    }
    @Test
    public void testIfResponseForecastDayTwoHasMinTemp() {
        assertNotEquals(null, forecastReport.getSecondDay().getMinTemperature());
    }

    @Test
    public void testIfResponseForecastDayThreeHasMaxTemp() {
        assertNotEquals(null, forecastReport.getThirdDay().getMaxTemperature());
    }
    @Test
    public void testIfResponseForecastDayThreeHasMinTemp() {
        assertNotEquals(null, forecastReport.getThirdDay().getMinTemperature());
    }

}
