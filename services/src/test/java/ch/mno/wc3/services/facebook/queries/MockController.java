package ch.mno.wc3.services.facebook.queries;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RestController
public class MockController {

    @GetMapping("/v15.0/video123/video_insights/total_video_views/lifetime")
    public String getVideoViews(@RequestParam("access_token") String accessToken) {
        return "{\"data\": [{\"name\": \"total_video_views\", \"values\": [{\"value\": 1357}]}]}";
    }

    @GetMapping(value = "/v15.0/appId/videos", produces = "application/json")
    public String getVideos(@RequestParam("access_token") String accessToken) throws IOException {
        String filename = "videos-" + accessToken + ".json";
        InputStream jsonStream = getClass().getResourceAsStream("/" + filename);
        assertNotNull(jsonStream, filename + " could not be found");
        return IOUtils.toString(jsonStream, StandardCharsets.UTF_8);
    }


    @GetMapping(value = "/v15.0/videoId/video_insights", produces = "application/json")
    public String getVideoInsights(@RequestParam("access_token") String accessToken) throws IOException {
        InputStream jsonStream = getClass().getResourceAsStream("/video-insights.json");
        assertNotNull(jsonStream, "video-insights.json could not be found");
        return IOUtils.toString(jsonStream, StandardCharsets.UTF_8);
    }

}