package ch.mno.wc3.services.facebook.queries;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

public class GetVideoConsumptionRateOperation extends AbstractOperation<Float> {

    private static final String PATH_TEMPLATE = "/{videoId}/video_insights/total_video_consumption_rate/lifetime";
    private final String videoId;
    private String pageAccessToken;

    public GetVideoConsumptionRateOperation(WebClient webClient, String pageAccessToken, String videoId) {
        super(webClient);
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
    public Float execute() {
        String json = download(buildUrl());
        return extractFloat(json, "value\":(?<value>[\\d\\.]+)");
    }

}