package utilities;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;

public class HttpUtilities {

    public int getHTTPStatus(String connectionUrl) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGetrequest = new HttpGet(connectionUrl);
        HttpResponse httpResponse = null;
        try {
            httpResponse = client.execute(httpGetrequest);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return httpResponse.getStatusLine().getStatusCode();
    }
}