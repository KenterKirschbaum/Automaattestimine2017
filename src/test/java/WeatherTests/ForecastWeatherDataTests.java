package WeatherTests;

import org.junit.Before;
import org.junit.Test;

public class ForecastWeatherDataTests   {
    private double longitudeOfTallinn;
    private double latitudeOfTallinn;
    private WeatherRequest weatherDataRequest;
    private ForecastWeatherDataRequest forecastWeatherDataRequest;

    @Before
    public void initialObjectsData()    {
        longitudeOfTallinn = 0;
        latitudeOfTallinn = 0;
        weatherDataRequest = new WeatherDataRequest();
    }

    @Test
    public void testIfResponseHasCoordinates() {
        assertNotEquals(null, forecastWeatherDataRequest.getLatitude());
        assertNotEquals(null, forecastWeatherDataRequest.getLongitude());
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
    public void testIfResponseHasMinAndMaxTemperatureValues() {
        assertNotEquals(null, forecastWeatherDataRequest.getMaxTemperature());
        assertNotEquals(null, forecastWeatherDataRequest.getMinTemperature());

    }

    @Test
    public void testIfResponseHasFiveDayForecastValues() {
        assertNotEquals(null, forecastWeatherData.getFirstDay());
        assertNotEquals(null, forecastWeatherData.getSecondDay());
        assertNotEquals(null, forecastWeatherData.getThirdDay());
        assertNotEquals(null, forecastWeatherData.getFourthDay());
        assertNotEquals(null, forecastWeatherData.getFifthDay());

    }

}
