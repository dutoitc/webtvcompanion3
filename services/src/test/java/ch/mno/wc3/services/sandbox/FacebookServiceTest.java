package ch.mno.wc3.services.sandbox;

import ch.mno.wc3.services.facebook.data.FBVideosJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FacebookServiceTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testConvertJsonToObject() throws IOException {
        // Read JSON
        InputStream jsonStream = getClass().getResourceAsStream("/fbvideos.json");
        assertNotNull(jsonStream, "fbvideos.json could not be found");

        // Convert
        FBVideosJson fbVideosJson = objectMapper.readValue(jsonStream, FBVideosJson.class);

        // Debug
        System.out.println(fbVideosJson);

        // Tests
        assertNotNull(fbVideosJson);
        assertEquals(25, fbVideosJson.getData().size());
        var datum = fbVideosJson.getData().getFirst();
        assertEquals("7238532507282706", datum.getId());
        assertEquals(372, datum.getDescription().length());
        assertEquals("complete", datum.getStatus().publishingPhase().status());
    }
}
