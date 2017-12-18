package apiMockingTests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import currentWeather.CurrentWeatherReport;
import currentWeather.CurrentWeatherRepository;
import utilities.Constants;
import weatherRequest.WeatherRequest;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class ApiMockingTests {
	
	public static CurrentWeatherRepository repository;

	@BeforeClass
    public static void setUpClass() throws IOException {
		if (Constants.mock) {
			repository = Mockito.mock(CurrentWeatherRepository.class);
        } else {
        	repository = new CurrentWeatherRepository();
        }
	}
	
	@Test
    public void testIfRequestedCityEqualsReportedCity() throws IOException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metic");
        if (Constants.mock) {
        	 when(repository.responseJsonDataToCurrentWeatherReport(weatherRequest))
        	 	.thenReturn(new CurrentWeatherReport("Tallinn", "EE", 22.2, 33.3, 3.0));
        }
        CurrentWeatherReport currentReport = repository.responseJsonDataToCurrentWeatherReport(weatherRequest);
        if (Constants.mock) verify(repository).responseJsonDataToCurrentWeatherReport(weatherRequest);
        assertEquals(weatherRequest.getCity(), currentReport.getCity());

    }
	
	@Test
    public void testIfRequestedCountryEqualsReportedCountry() throws IOException {
        WeatherRequest weatherRequest = new WeatherRequest("Tallinn", "EE", "metic");
        if (Constants.mock) {
        	 when(repository.responseJsonDataToCurrentWeatherReport(weatherRequest))
        	 	.thenReturn(new CurrentWeatherReport("Tallinn", "EE", 22.2, 33.3, 3.0));
        }
        CurrentWeatherReport currentReport = repository.responseJsonDataToCurrentWeatherReport(weatherRequest);
        if (Constants.mock) verify(repository).responseJsonDataToCurrentWeatherReport(weatherRequest);
        assertEquals(weatherRequest.getCountry(), currentReport.getCountry());

    }
	

}