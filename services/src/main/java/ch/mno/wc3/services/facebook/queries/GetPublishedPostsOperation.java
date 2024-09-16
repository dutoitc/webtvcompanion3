package ch.mno.wc3.services.facebook.queries;

import ch.mno.wc3.services.facebook.data.FBPagePost;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

public class GetPublishedPostsOperation extends AbstractOperation<FBPagePost> {

    private static final String PATH_TEMPLATE = "/{appId}/published_posts";
    private String appId;
    private String tokenAccess;

    public GetPublishedPostsOperation(WebClient webClient, String appId, String tokenAccess) {
        super(webClient);
        this.appId = appId;
        this.tokenAccess = tokenAccess;
    }

    @Override
    protected String buildUrl() {
        var builder = UriComponentsBuilder.fromUriString(baseURL)
                .path(PATH_TEMPLATE)
                .queryParam("fields", "name,values")
                .queryParam("access_token", tokenAccess)
                .queryParam("debug", "all");
        BASIC_OPTIONS.forEach(builder::queryParam);
        return builder.buildAndExpand(appId).encode().toUriString();
    }

    @Override
    public FBPagePost execute() {
        return download(buildUrl(), FBPagePost.class);
    }
}