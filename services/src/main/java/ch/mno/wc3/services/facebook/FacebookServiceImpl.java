package ch.mno.wc3.services.facebook;

import ch.mno.wc3.services.facebook.config.FacebookConfig;
import ch.mno.wc3.services.facebook.data.FBAttachments;
import ch.mno.wc3.services.facebook.data.FBPagePost;
import ch.mno.wc3.services.facebook.data.FBVideosData;
import ch.mno.wc3.services.facebook.queries.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.LinkedHashMap;

@Service
@AllArgsConstructor
public class FacebookServiceImpl {

    private final WebClient webClient;
    private FacebookConfig config;

    public FBVideosData getVideos() {
        return new GetVideoOperation(webClient, config).execute();
    }

    public int getVideoViews(String videoId) {
        return new GetVideoViewsOperation(webClient, config.getTokenPage(), videoId).execute();
    }

    public float getVideoConsumptionRateOperation(String videoId) {
        return new GetVideoConsumptionRateOperation(webClient, config.getTokenPage(), videoId).execute();
    }

    public int getVideoTotalVideoCompleteViewsUnique(String videoId) {
        return new GetVideoTotalVideoCompleteViewsUniqueOperation(webClient, config.getTokenPage(), videoId).execute();
    }

    public LinkedHashMap<String, String> getVideoInsights(String videoId) {
        return new GetVideoInsightsOperation(webClient, config.getTokenPage(), videoId).execute();
    }


    void dumpPublishedPosts() {
        new GetPublishedPostsOperation(webClient, config.getAppId(), config.getTokenApp()).execute().dump();
    }

    public FBPagePost getScheduledPosts() {
        return new GetScheduledPostsOperation(webClient, config.getAppId(), config.getTokenPage()).execute();
    }

    public FBAttachments getPostAttachment(String postId) {
        return new GetPostAttachmentOperation(webClient, config.getTokenPage(), postId).execute();
    }


}
