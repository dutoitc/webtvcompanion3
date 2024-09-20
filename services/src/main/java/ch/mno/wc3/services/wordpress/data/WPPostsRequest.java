package ch.mno.wc3.services.wordpress.data;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@Slf4j
public class WPPostsRequest {

    private String baseUrl;

    private static final String PATH = "/wp-json/wp/v2/posts?per_page=100";

    public WPListResult execute() {
        WPListResult listResult = new WPListResult();
        int pageNumber = 1;

        while (true) {
            String url = String.format("%s&page=%d", baseUrl + PATH, pageNumber++);
            log.info("Calling {}", url);

            String json = load(url);
            if (json == null) {
                break;
            }

            listResult.addBatch(json);
        }

        return listResult;
    }

    private String load(String url) {
        try (var inputStream = new URI(url).toURL().openStream()) {
            String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            if (json.contains("rest_post_invalid_page_number")) {
                return null;
            }
            return json;
        } catch (IOException|URISyntaxException e) {
            log.warn("Error loading URL {}: {}", url, e.getMessage());
            return null;
        }
    }
}
