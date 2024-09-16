package ch.mno.wc3.services.youtube.queries;

import ch.mno.wc3.services.ServiceException;
import ch.mno.wc3.services.youtube.data.YTVideoData;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.Video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * List channel snippet, then load snippet,contentDetails,status,topicDetails
 */
// TODO: tester video list sans Id pour tout récupérer, moins coûteux en quota ? https://developers.google.com/youtube/v3/docs/videos/list?apix_params=%7B%22part%22%3A%5B%22snippet%2CcontentDetails%2Cstatistics%22%5D%2C%22id%22%3A%5B%22XwRG8yrO18M%22%5D%7D
public class VideoListQuery extends AbstractQuery<List<YTVideoData>> {

    private final String channelId;

    public VideoListQuery(YouTube youtubeService, String channelId) {
        super(youtubeService);
        this.channelId = channelId;
    }

    public List<YTVideoData> execute() {
        var vdList = new ArrayList<YTVideoData>();

        try {
            String nextPageToken=null;
            boolean shouldContinue = true;
            while (shouldContinue) {
                shouldContinue = false;
                YouTube.Search.List request2 = youtubeService.search().list("snippet");
                if (nextPageToken != null && !nextPageToken.isBlank()) request2.setPageToken(nextPageToken);
                SearchListResponse response2 = request2//.setForMine(true)
                        .setMaxResults(2500L)
                        .setChannelId(channelId)
                        .setType("video")
                        .execute();
                response2.getItems().forEach(channelSearchResult -> addVideoDataToList(vdList, channelSearchResult.getId().getVideoId()));
                if (response2.getNextPageToken()!=null && !response2.getNextPageToken().isEmpty()) {
                    nextPageToken = response2.getNextPageToken();
                    shouldContinue = true;
                }
            }
            return vdList;
        } catch (IOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private void addVideoDataToList(ArrayList<YTVideoData> vdList, String videoId) {
        try {
            var responseVideo = youtubeService.videos()
                    .list("snippet,status,statistics") // contentDetails https://developers.google.com/youtube/v3/docs/videos/list?apix_params=%7B%22part%22%3A%5B%22snippet%2CcontentDetails%2Cstatistics%22%5D%2C%22id%22%3A%5B%22XwRG8yrO18M%22%5D%7D&apix=true
                    .setId(videoId)
                    .execute();
            Video item0 = responseVideo.getItems().get(0);
            vdList.add(VideoMapper.map(item0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}