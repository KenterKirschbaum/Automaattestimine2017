package weatherRequest;


public class WeatherRequest {
    private String city;
    private String country;
    private String metricFormat;

    public WeatherRequest(String city, String country, String format) {
        this.city = city;
        this.country = country;
        this.metricFormat = format;
    }

    public String getCity() {
            return city;
        }

    public String getCountry() {
            return country;
        }

    public String getMetricFormat() { return metricFormat;}

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setMetricFormat(String metricFormat) {
        this.metricFormat = metricFormat;
    }

    @Override
    public String toString()    {
        return "WeatherRequest{" + "city='" + city + '\'' + ", country='" + country + '\'' + ", format='" + metricFormat + '\'' + '}';

    }
}
