package weatherForecast;

public class ForecastReport {
    private String city;
    private String country;
    private double longitude;
    private double latitude;
    private OneDayWeatherReport firstDay;
    private OneDayWeatherReport secondDay;
    private OneDayWeatherReport thirdDay;

    public ForecastReport(String city, String country, double longitude, double latitude, OneDayWeatherReport firstDay,
                          OneDayWeatherReport secondDay, OneDayWeatherReport thirdDay) {
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.firstDay = firstDay;
        this.secondDay = secondDay;
        this.thirdDay = thirdDay;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public OneDayWeatherReport getFirstDay() {
        return firstDay;
    }

    public OneDayWeatherReport getSecondDay() {
        return secondDay;
    }

    public OneDayWeatherReport getThirdDay() { return thirdDay; }

	@Override
	public String toString() {
		return "ForecastReport [city=" + city + ", country=" + country + ", longitude=" + longitude + ", latitude="
				+ latitude + ", firstDay=" + firstDay + ", secondDay=" + secondDay + ", thirdDay=" + thirdDay
				+ ", getCity()=" + getCity() + ", getCountry()=" + getCountry() + ", getLongitude()=" + getLongitude()
				+ ", getLatitude()=" + getLatitude() + ", getFirstDay()=" + getFirstDay() + ", getSecondDay()="
				+ getSecondDay() + ", getThirdDay()=" + getThirdDay() + "]";
	}
    
    
}
