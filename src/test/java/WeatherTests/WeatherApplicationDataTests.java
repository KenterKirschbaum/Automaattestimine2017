package WeatherTests;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class WeatherApplicationDataTests {

    @Test
    public void testWeatherAtTheMoment  {
        assertTrue(weather.hasCityCoordinates());
        assertTrue(weather.hasMeasuredTemperature());
        assertTrue(weather.hasFeelsLikeTemperature());
        assertTrue(weather.hasWindDirection());
        assertTrue(weather.hasWindSpeedValue());
        assertTrue(weather.hasBarometerValue());
        assertTrue(weather.hasDewPoint());
        assertTrue(weather.hasHumidutyValue());
        assertTrue(weather.hasVisibility());
        assertTrue(weather.hasSunRiseTime());
        assertTrue(weather.hasSunSetTime());
    }

    @Test
    public void test3DayForecastMinimumMaximumTemperature   {
        assertTrue(weather.hasCityCoordinates());
        assertTrue(weather.hasMinimumTemperature());
        assertTrue(weather.hasMaximumTemperature());
    }

    @Test
    public void testCityCoordinates {
        String cityCoordinates = "";
        assertEquals(cityCoordinates, weather.getCityCoordinates());
    }
}
