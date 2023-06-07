package tomato.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPClient {
    private URL url;

    public HTTPClient(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void POST(String json) {
        //send json to url
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            System.out.println("sending json");
            connection.getOutputStream().write(json.toString().getBytes());

            int responseCode = connection.getResponseCode();
            System.out.println("response code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                System.out.println("success");
            } else {
                System.out.println("failure");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
