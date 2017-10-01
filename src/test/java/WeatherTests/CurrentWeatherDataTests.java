package WeatherTests;

import org.junit.Test;
import org.junit.Before;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class CurrentWeatherDataTests {
    private double longitudeOfTallinn;
    private double latitudeOfTallinn;
    private WeatherRequest weatherDataRequest;
    private CurrentWeatherDataRequest currentWeatherData;

    @Before
    public void initialObjectsData()    {
        weatherDataRequest = new WeatherDataRequest();
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
        assertNotEquals(null, currentWeatherData.getTemp());
    }
}
