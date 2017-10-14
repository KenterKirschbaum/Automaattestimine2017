package weatherTests;

import org.junit.Before;
import org.junit.Test;
import weatherRequest.WeatherRequest;

import static org.junit.Assert.assertEquals;

public class CurrentWeatherDataTests {
    private double longitudeOfTallinn;
    private double latitudeOfTallinn;
    private WeatherRequest weatherDataRequest;
    private CurrentWeatherDataRequest currentWeatherData;

    @Before
    public void initialObjectsData()    {
        weatherRequest = new WeatherRequest();
        longitudeOfTallinn = 0;
        latitudeOfTallinn = 0;
    }

    @Test
    public void testIfResponseHasCoordinates() {
        assertNotEquals(null, currentWeatherData.getLatitude());
        assertNotEquals(null, currentWeatherData.getLongitude());
    }

    @Test
    public void testIfResponseCoordinateValuesMatchTallinnCoordinatesValue() {
        assertEquals(weatherDataRequest.getLatitude(),latitudeOfTallinn);
        assertEquals(weatherDataRequest.getLongitude(),longitudeOfTallinn);
    }

    @Test
    public void testIfResponseCityValueMatchRequestedCityValue() {
        assertEquals(weatherDataRequest.getCity(),currentWeatherData.getCity());
    }

    @Test
    public void testIfResponseHasTemperatureValue() {
        assertNotEquals(null, currentWeatherData.getTemperature());
    }
}
