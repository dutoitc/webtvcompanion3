package ch.mno.wc3.services.youtube;

import ch.mno.wc3.services.youtube.data.YTVideoData;
import com.google.api.services.youtube.model.Video;

import java.util.List;

public interface YoutubeService {

    List<YTVideoData> findVideoData();

    /** Status: need check if working, test error on manuel test */
    Video findScheduledVideoStatus(String idVideoYT);

}