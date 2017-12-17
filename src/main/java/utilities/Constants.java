package utilities;

public class Constants {

    public enum COUNTRYCODE {
        EE, UK;

        public String toString() {
            switch(this){
                case EE :
                    return "EE";
                case UK :
                    return "UK";
            }
            return null;
        }
    }
}
