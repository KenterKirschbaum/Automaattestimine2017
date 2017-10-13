package weatherForecast;


public class OneDayWeatherReport {
    private double minTemperature;
    private double maxTemperature;


    public OneDayWeatherReport(double minTemperature, double maxTemperature) {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }
}
