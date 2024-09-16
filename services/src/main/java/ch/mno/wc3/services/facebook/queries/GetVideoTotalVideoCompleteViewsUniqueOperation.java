package ch.mno.wc3.services.facebook.queries;

import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

public class GetVideoTotalVideoCompleteViewsUniqueOperation extends AbstractOperation<Integer> {

    private static final String PATH_TEMPLATE = "/{videoId}/video_insights/total_video_complete_views_unique/lifetime";

    private final String videoId;
    private final String pageAccessToken;

    public GetVideoTotalVideoCompleteViewsUniqueOperation(WebClient webClient, String pageAccessToken, String videoId) {
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
        String json = download(buildUrl());
        return extractInt(json,"value\":\\s*(?<value>[\\d\\.]+)" );
    }

}