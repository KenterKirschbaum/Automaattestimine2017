package httpUtilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtilities {

    public static HttpURLConnection makeHttpURLRequest(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        return (HttpURLConnection) url.openConnection();
    }
}