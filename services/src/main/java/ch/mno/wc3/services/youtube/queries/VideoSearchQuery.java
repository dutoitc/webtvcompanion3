package ch.mno.wc3.services.youtube.queries;

import ch.mno.wc3.services.ServiceException;
import ch.mno.wc3.services.youtube.data.YTVideoSummary;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Search for all videos.
 * Uses 100 credits per request.
 */
public class VideoSearchQuery extends AbstractQuery<List<YTVideoSummary>> {

    private final String channelId;

    public VideoSearchQuery(YouTube youtubeService, String channelId) {
        super(youtubeService);
        this.channelId = channelId;
    }

    @Override
    public List<YTVideoSummary> execute() {
        List<YTVideoSummary> videoList = new ArrayList<>();
        String nextPageToken = null;

        try {
            do {
                SearchListResponse response = executeSearchRequest(nextPageToken);
                response.getItems().stream()
                        .map(this::mapToVideoSummary)
                        .forEach(videoList::add);

                nextPageToken = response.getNextPageToken();

            } while (nextPageToken != null && !nextPageToken.isEmpty());

            return videoList;

        } catch (IOException e) {
            throw new ServiceException("Failed to fetch video list: " + e.getMessage(), e);
        }
    }

    private SearchListResponse executeSearchRequest(String nextPageToken) throws IOException {
        YouTube.Search.List request = youtubeService.search().list("snippet")
                .setMaxResults(50L) // Note: maxResults cannot exceed 50 for this API
                .setChannelId(channelId)
                .setType("video");

        Optional.ofNullable(nextPageToken)
                .filter(token -> !token.isBlank())
                .ifPresent(request::setPageToken);

        return request.execute();
    }

    private YTVideoSummary mapToVideoSummary(SearchResult r) {
        var t = LocalDateTime.ofEpochSecond(r.getSnippet().getPublishedAt().getValue() / 1000, 0, ZoneOffset.UTC);
        return YTVideoSummary.of(r.getId().getVideoId(), r.getSnippet().getTitle(), r.getSnippet().getDescription(), t);
    }

}