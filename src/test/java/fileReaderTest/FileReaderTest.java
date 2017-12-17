package fileReaderTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fileReader.FileReader;
import weatherRequest.WeatherRequest;

public class FileReaderTest {

	private FileReader fileReader;
    private String city;
    private String countryCode;
    private String units;
	private WeatherRequest fileContent;
    

    @Before
    public void initObjects() {
        fileReader = new FileReader();
        city = "Tallinn";
        countryCode = "EE";
        units = "metric";
        fileContent = fileReader.readInputDataFromInputFile();
        System.out.println(fileContent);
    }

    @Test
    public void testIfWeatherRequestCityNameMatches() {
        assertEquals(city, fileContent.getCity());
    }

    @Test
    public void testIfWeatherRequestCountryMatches() {
    	assertEquals(countryCode, fileContent.getCountry()); }

    @Test
    public void testIfWeatherRequestUnitMatches() {
        assertEquals(units, fileContent.getMetricFormat());
    }

}