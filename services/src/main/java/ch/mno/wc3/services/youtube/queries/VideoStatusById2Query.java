package ch.mno.wc3.services.youtube.queries;

import ch.mno.wc3.services.ServiceException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;

// https://developers.google.com/youtube/v3/docs/videos/list?hl=fr&apix_params=%7B%22part%22%3A%5B%22snippet%2Cstatus%2CcontentDetails%2CfileDetails%22%5D%2C%22id%22%3A%5B%22POi1FEq3Wm4%2C1nH0BHJmc2Y%22%5D%2C%22fields%22%3A%22items(id%2Csnippet(title%2Cdescription%2CcategoryId)%2Cstatus(uploadStatus%2CprivacyStatus%2CpublishAt%2CmadeForKids%2CselfDeclaredMadeForKids)%2CcontentDetails(definition%2ChasCustomThumbnail)%2CfileDetails(fileName%2CvideoStreams%2F*%2CaudioStreams%2F*%2CdurationMs%2CbitrateBps))%22%7D
// Status: not working or auth error ?
public class VideoStatusById2Query extends AbstractQuery<Video> {

    private final String videoId;

    public VideoStatusById2Query(YouTube youtubeService, String videoId) {
        super(youtubeService);
        this.videoId = videoId;
    }

    public Video execute() {
        try {
            YouTube.Videos.List request = youtubeService.videos()
                    .list("snippet,status,contentDetails,fileDetails");
            VideoListResponse response = request
                    .setMaxResults(2500L)
                    .setId(videoId)
                    .setFields("items(id,snippet(title,description,categoryId),status(uploadStatus,privacyStatus,publishAt,madeForKids,selfDeclaredMadeForKids),contentDetails(definition,hasCustomThumbnail),fileDetails(fileName,videoStreams/*,audioStreams/*,durationMs,bitrateBps))")
                    .execute();

            System.out.println(response);
            if (response.getItems().isEmpty()) return null;
            return response.getItems().get(0);
        } catch (IOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}