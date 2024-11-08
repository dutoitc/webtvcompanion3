package ch.mno.wc3.services.youtube;

import ch.mno.wc3.services.youtube.data.YTVideoData;
import ch.mno.wc3.services.youtube.data.YTVideoSummary;
import com.google.api.services.youtube.model.Video;

import java.util.List;

public interface YoutubeService {

    //    @Override
//    @Deprecated
//    public List<YTVideoData> findVideoData() {
//        var videoDataList = new VideoListQuery(youtubeService, config.getChanelId()).execute();
//        videoDataList.sort(Comparator.comparing(YTVideoData::getPublicationDate).reversed());
//        return videoDataList;
//    }


    /** Usage cost 100 credits */
    List<YTVideoSummary> findVideosSummaries();

    /** Usage cost 1 credits */
    YTVideoData findVideoData(String videoId);

    /** Status: need check if working, test error on manuel test */
    Video findScheduledVideoStatus(String idVideoYT);

}