package ch.mno.wc3.services.facebook.queries;

import ch.mno.wc3.services.ServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

public class GetVideoViewsOperation extends AbstractOperation<Integer> {

    private static final String PATH_TEMPLATE = "/{videoId}/video_insights/total_video_views/lifetime";

    private final String videoId;
    private final String pageAccessToken;

    public GetVideoViewsOperation(WebClient webClient, String pageAccessToken, String videoId) {
        super(webClient);
        Assert.hasText(pageAccessToken, "Page access token cannot be null or empty");
        Assert.hasText(videoId, "Video ID cannot be null or empty");
        this.pageAccessToken = pageAccessToken;
        this.videoId = videoId;
    }

    @Override
    protected String buildUrl() {
        var builder = UriComponentsBuilder.fromUriString(baseURL)
                .path(PATH_TEMPLATE)
                .queryParam("access_token", pageAccessToken);
        BASIC_OPTIONS.forEach(builder::queryParam);
        return builder.buildAndExpand(videoId).encode().toUriString();
    }

    @Override
    public Integer execute() {
        String json = download(buildUrl());;

        JsonNode rootNode = parseJson(json);
        JsonNode valueNode = rootNode.path("data").get(0).path("values").get(0).path("value");
        if (valueNode.isInt()) {
            return valueNode.asInt();
        } else {
            throw new ServiceException("Value not found or not an integer in JSON: " + json);
        }
    }

}
