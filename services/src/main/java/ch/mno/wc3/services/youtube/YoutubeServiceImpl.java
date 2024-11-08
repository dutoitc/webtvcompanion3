package ch.mno.wc3.services.youtube;

import ch.mno.wc3.services.youtube.config.YoutubeConfig;
import ch.mno.wc3.services.youtube.data.YTVideoData;
import ch.mno.wc3.services.youtube.data.YTVideoSummary;
import ch.mno.wc3.services.youtube.queries.VideoDataQuery;
import ch.mno.wc3.services.youtube.queries.VideoSearchQuery;
import ch.mno.wc3.services.youtube.queries.VideoStatusById2Query;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Comparator;
import java.util.List;

// quota: https://developers.google.com/youtube/v3/determine_quota_cost?hl=fr
@Service
public class YoutubeServiceImpl implements YoutubeService {

    private YoutubeConfig config;

    private YouTube youtubeService;

    public YoutubeServiceImpl(YoutubeConfig config) throws GeneralSecurityException, IOException {
        this.config = config;
        youtubeService = new YouTubeFactory2().build(config.getApplicationName(), config.getClientSecretsFile());
    }

    /** Usage cost 300 credits */
//    @Override
//    @Deprecated
//    public List<YTVideoData> findVideoData() {
//        var videoDataList = new VideoListQuery(youtubeService, config.getChanelId()).execute();
//        videoDataList.sort(Comparator.comparing(YTVideoData::getPublicationDate).reversed());
//        return videoDataList;
//    }

    @Override
    public List<YTVideoSummary> findVideosSummaries() {
        var list = new VideoSearchQuery(youtubeService, config.getChanelId()).execute();
        list.sort(Comparator.comparing(YTVideoSummary::getPublicationDate).reversed());
        return list;
    }

    @Override
    public YTVideoData findVideoData(String videoId) {
        return new VideoDataQuery(youtubeService, videoId).execute();
    }


    @Override
    public Video findScheduledVideoStatus(String idVideoYT) {
        return new VideoStatusById2Query(youtubeService, idVideoYT).execute();
    }

}