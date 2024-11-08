package ch.mno.wc3.services.facebook.queries;

import ch.mno.wc3.services.ServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

public class GetReelViewsOperation extends AbstractOperation<Integer> {

    private static final String PATH_TEMPLATE = "/{reelId}";
//
//    GOT https://graph.facebook.com/v21.0/908772733999723?fields=views?
//        // access_token=EABJ2om8HpxgBOwqiiM4vDZBQZCEupfhm6Euu2qRdOPdTW0zex3Vz3jHVlEa2Cf4DjqJ3jFP8IZBgZBCLktrCwpKvgciDpK9LyayJKEZAm8BZCefrjQLtCEFAao5HdOCQqlvMllADb0orms6FISGZAXLZAZCZATKsYRtWjCNwkDPeOeIUzAkf911lzqrleg
//        // &transport=cors&format=json&pretty=0&method=get&suppress_http_code=1
//    EXP https://graph.facebook.com/v21.0/908772733999723?fields=views&
//    // access_token=EABJ2om8HpxgBOwqiiM4vDZBQZCEupfhm6Euu2qRdOPdTW0zex3Vz3jHVlEa2Cf4DjqJ3jFP8IZBgZBCLktrCwpKvgciDpK9LyayJKEZAm8BZCefrjQLtCEFAao5HdOCQqlvMllADb0orms6FISGZAXLZAZCZATKsYRtWjCNwkDPeOeIUzAkf911lzqrleg

    private final String reelId;
    private final String accessToken;

    public GetReelViewsOperation(WebClient webClient, String accessToken, String reelId) {
        super(webClient);
        Assert.hasText(accessToken, "Page access token cannot be null or empty");
        Assert.hasText(reelId, "Video ID cannot be null or empty");
        this.accessToken = accessToken;
        this.reelId = reelId;
    }

    @Override
    protected String buildUrl() {
        var builder = UriComponentsBuilder.fromUriString(baseURL)
                .path(PATH_TEMPLATE)
                .queryParam("access_token", accessToken)
                .queryParam("fields", "views");
        BASIC_OPTIONS.forEach(builder::queryParam);
        return builder.buildAndExpand(reelId).encode().toUriString();
    }

    @Override
    public Integer execute() {
        String json = download(buildUrl());

        JsonNode rootNode = parseJson(json);
        JsonNode data = rootNode.path("views");
        if (data==null) {
            throw new ServiceException("No 'views' found in JSON: " + json);
        }
        JsonNode valueNode = data;
        if (valueNode.isInt()) {
            return valueNode.asInt();
        } else {
            throw new ServiceException("Value not found or not an integer in JSON: " + json);
        }
    }

}
