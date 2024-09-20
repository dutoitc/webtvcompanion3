package ch.mno.wc3.services.wordpress.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

@Slf4j
public class WPListResult {
    @Getter
    private WPVideosData videosData;
    private ObjectMapper mapper;

    public WPListResult() {
        this.videosData = new WPVideosData();
        this.mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void addBatch(String json) {
        try {
            WPVideosData currentBatch = mapper.readValue(json, WPVideosData.class);
            currentBatch.forEach(video ->
                    video.getTitle().setRendered( HtmlUtils.htmlEscape(video.getTitle().getRendered()))
            );
            videosData.addAll(currentBatch);
        } catch (IOException e) {
            log.warn("Error processing JSON: {}", e.getMessage());
        }
    }
}
