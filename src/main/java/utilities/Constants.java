package utilities;

public class Constants {

    public enum COUNTRYCODE {
        EE, US;

        public String toString() {
            switch(this){
                case EE :
                    return "EE";
                case US :
                    return "UK";
            }
            return null;
        }
    }

    public enum UNIT {
        metric, imperial;

        public String toString() {
            switch (this) {
                case metric:
                    return "metric";
                case imperial:
                    return "imperial";
            }
            return null;
        }
    }
}
