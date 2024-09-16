package ch.mno.wc3.services.facebook.queries;

import ch.mno.wc3.services.facebook.data.FBPagePost;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

public class GetScheduledPostsOperation extends AbstractOperation<FBPagePost> {

    private static final String PATH_TEMPLATE = "/{appId}/scheduled_posts";
    private String appId;
    private String pageAccessToken;

    public GetScheduledPostsOperation(WebClient webClient, String appId, String pageAccessToken) {
        super(webClient);
        this.appId = appId;
        this.pageAccessToken = pageAccessToken;
    }

    @Override
    protected String buildUrl() {
        var builder = UriComponentsBuilder.fromUriString(baseURL)
                .path(PATH_TEMPLATE)
                .queryParam("fields", "scheduled_publish_time,created_time,id,message,title")
                .queryParam("access_token", pageAccessToken);
        BASIC_OPTIONS.forEach(builder::queryParam);
        return builder.buildAndExpand(appId).encode().toUriString();
    }

    @Override
    public FBPagePost execute() {
        var posts = download(buildUrl(), FBPagePost.class);
        var allPosts = posts;
        while (posts.getPaging() != null && posts.getPaging().getNext() != null) {
            posts = download(posts.getPaging().getNext(), FBPagePost.class);
            allPosts.add(posts);
        }
        return allPosts;
    }


}
