package consoleScanner;

import utilities.Constants;
import weatherRequest.WeatherRequest;

import java.util.Scanner;

public class ConsoleScanner {

    public WeatherRequest buildWeatherRequestFromConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        String city;
        String countryCode;
        String units;
        System.out.println("Enter city name: ");

        while (true) {
            city = scanner.nextLine();
            if (isValidCity(city)) {
                break;
            } else {
                System.out.println("Not a valid city. Try again");
            }
        }

        System.out.println("Enter 2 letter country code: ");
        while (true) {
            countryCode = scanner.nextLine();
            if (isValidCountryCode(countryCode)) {
                break;
            } else {
                System.out.println("Not a valid country code. Try again");
            }
        }


        WeatherRequest weatherRequest = new WeatherRequest(city, countryCode, "metric");
        return weatherRequest;
    }

    public boolean isValidCity(String city) {
        if (!city.isEmpty() && city.matches("[a-zA-Z]+")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidCountryCode(String countryCode) {
        for (Constants.COUNTRYCODE code: Constants.COUNTRYCODE.values()) {
            if (code.toString().equals(countryCode)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ConsoleScanner consoleScanner = new ConsoleScanner();
        consoleScanner.buildWeatherRequestFromConsoleInput();
    }
}
