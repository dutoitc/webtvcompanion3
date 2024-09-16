package ch.mno.wc3.services.youtube;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;

public class YouTubeFactory2 {


    private static final Collection<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/youtube");

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();


    /**
     * Build and return an authorized API client service.
     *
     * @param applicationName
     * @param clientSecrets
     */
    public YouTube build(String applicationName, String clientSecrets) throws GeneralSecurityException, IOException {

        // https://github.com/googleapis/google-auth-library-java
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(new FileInputStream(clientSecrets));
        credentials = credentials.createScoped(SCOPES);
        credentials.refreshIfExpired();

        // Create a HttpRequestInitializer, which will provide a baseline configuration to all requests.
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        return new YouTube.Builder(httpTransport, JSON_FACTORY, requestInitializer)
                .setApplicationName(applicationName)
                .build();
    }

}
