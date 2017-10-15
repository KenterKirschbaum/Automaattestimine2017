package weatherForecast;


public class OneDayWeatherReport {
    private double minTemperatureValue;
    private double maxTemperatureValue;


    public OneDayWeatherReport(double minTemperature, double maxTemperature) {
        this.minTemperatureValue = minTemperature;
        this.maxTemperatureValue = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperatureValue;
    }

    public double getMaxTemperature() {
        return maxTemperatureValue;
    }

}
