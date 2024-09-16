package ch.mno.wc3.services.facebook.queries;

import ch.mno.wc3.services.facebook.data.FBAttachments;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

public class GetPostAttachmentOperation extends AbstractOperation<FBAttachments> {

    private static final String PATH_TEMPLATE = "/{postId}/attachments";
    private final String postId;
    private String pageAccessToken;

    public GetPostAttachmentOperation(WebClient webClient, String pageAccessToken, String postId) {
        super(webClient);
        this.pageAccessToken = pageAccessToken;
        this.postId = postId;
    }

    @Override
    protected String buildUrl() {
        var builder = UriComponentsBuilder.fromUriString(baseURL)
                .path(PATH_TEMPLATE)
                .queryParam("access_token", pageAccessToken);
        BASIC_OPTIONS.forEach(builder::queryParam);
        return builder.buildAndExpand(postId).encode().toUriString();
    }

    @Override
    public FBAttachments execute() {
        return download(buildUrl(), FBAttachments.class);
    }
}
