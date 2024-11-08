package ch.mno.wc3.services.youtube.queries;

import ch.mno.wc3.services.youtube.data.YTVideoData;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;

import java.io.IOException;

/**
 * List channel snippet, then load snippet,contentDetails,status,topicDetails
 */
// TODO: only search for recent video. Then read stats for all video
// https://developers.google.com/youtube/v3/docs/search/list?hl=fr
// https://developers.google.com/youtube/v3/docs/videos/list?apix_params=%7B%22part%22%3A%5B%22snippet%2CcontentDetails%2Cstatistics%22%5D%2C%22id%22%3A%5B%22XwRG8yrO18M%22%5D%7D
public class VideoDataQuery extends AbstractQuery<YTVideoData> {

    private final String videoId;

    public VideoDataQuery(YouTube youtubeService, String videoId) {
        super(youtubeService);
        this.videoId = videoId;
    }


    @Override
    public YTVideoData execute() {
        try {
            var responseVideo = youtubeService.videos()
                    .list("snippet,status,statistics") // 1 credit; contentDetails https://developers.google.com/youtube/v3/docs/videos/list?apix_params=%7B%22part%22%3A%5B%22snippet%2CcontentDetails%2Cstatistics%22%5D%2C%22id%22%3A%5B%22XwRG8yrO18M%22%5D%7D&apix=true
                    .setId(videoId)
                    .execute();
            Video item0 = responseVideo.getItems().get(0);
            return VideoMapper.map(item0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
