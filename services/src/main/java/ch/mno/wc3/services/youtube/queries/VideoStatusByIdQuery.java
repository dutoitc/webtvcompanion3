package ch.mno.wc3.services.youtube.queries;

import ch.mno.wc3.services.youtube.data.YTStatus;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;

// TODO https://developers.google.com/youtube/v3/docs/videos/list?hl=fr&apix_params=%7B%22part%22%3A%5B%22status%22%5D%2C%22id%22%3A%5B%22POi1FEq3Wm4%22%5D%7D&apix=true
// marche pas, seulement dans le truc de youtube
public class VideoStatusByIdQuery extends AbstractQuery<YTStatus> {

    private final String videoId;

    public VideoStatusByIdQuery(YouTube youtubeService, String videoId) {
        super(youtubeService);
        this.videoId = videoId;
    }

    public YTStatus execute() {
        try {
//            YouTube.Videos.List request = youtubeService.videos().list("status");
//            VideoListResponse response = request.setId(videoId).execute();
//            return VideoMapper.mapStatus(response.getItems().get(0).getStatus());

            YouTube.Videos.List request = youtubeService.videos()
                    .list("status");
            VideoListResponse response = request.setId("POi1FEq3Wm4").execute();
            System.out.println(response);
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}