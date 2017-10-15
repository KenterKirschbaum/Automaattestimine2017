package httpUtilitiesTest;

import currentWeather.CurrentWeatherRepository;
import httpUtilities.HttpUtilities;
import org.junit.Before;
import org.junit.Test;
import weatherForecast.ForecastRepository;
import weatherRequest.WeatherRequest;

import static org.junit.Assert.assertEquals;

public class HttpUtilityTest {

    private WeatherRequest weatherRequest;
    private CurrentWeatherRepository currentWeatherRepository;
    private ForecastRepository forecastRepository;
    private HttpUtilities httpUtilities;
    private String currentWeatherURL;
    private String forecastWeatherURL;

    @Before
    public void initialObjects()    {
        weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        currentWeatherRepository = new CurrentWeatherRepository();
        currentWeatherURL = currentWeatherRepository.buildCurrentWeatherURL(weatherRequest);
        forecastRepository = new ForecastRepository();
        forecastWeatherURL = ForecastRepository.buildForecastWeatherURL(weatherRequest);
        httpUtilities = new HttpUtilities();
    }

    @Test
    public void testIfCurrentWeatherResponseStatusIsValid() {
        assertEquals(200, httpUtilities.getHTTPStatus(currentWeatherURL));
    }

    @Test
    public void testIfWeatherForecastResponseStatusIsValid() {
        assertEquals(200, httpUtilities.getHTTPStatus(forecastWeatherURL));
    }

}
