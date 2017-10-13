package currentWeather;

public class CurrentWeatherReport   {
    private String city;
    private String country;
    private double latitude;
    private double longitude;
    private double temperature;

    public CurrentWeatherReport(String city, String country, double latitude, double longitude, double temperature) {
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
    }

    public String getCity() { return city; }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getTemperature() {
        return temperature;
    }

}
