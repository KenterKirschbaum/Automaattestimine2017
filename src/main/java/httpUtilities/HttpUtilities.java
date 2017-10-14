package httpUtilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtilities {

    public static HttpURLConnection makeHttpURLRequest(String requestUrl) throws IOException {
        URL connetcionUrl = new URL(requestUrl);
        return (HttpURLConnection) connetcionUrl.openConnection();
    }
}