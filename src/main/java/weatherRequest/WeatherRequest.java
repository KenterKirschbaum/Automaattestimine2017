package weatherRequest;

public class WeatherRequest {

    public class WeatherRequest {
        private String city;
        private String country;
        private String format;

        public WeatherRequest(String city, String country, String format) {
            this.city = city;
            this.country = country;
            this.format = format;
        }

        public String getCity() {
            return city;
        }

        public String getCountry() {
            return country;
        }

        public String getFormat() {
            return format;
        }

}
