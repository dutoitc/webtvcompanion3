package ch.mno.wc3.services.youtube;

import ch.mno.wc3.services.youtube.config.YoutubeConfig;
import ch.mno.wc3.services.youtube.data.YTVideoData;
import ch.mno.wc3.services.youtube.queries.VideoListQuery;
import ch.mno.wc3.services.youtube.queries.VideoStatusById2Query;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class YoutubeServiceImpl implements YoutubeService {

    private final WebClient webClient;
    private YoutubeConfig config;

    private YouTube youtubeService;

    public YoutubeServiceImpl(WebClient webClient, YoutubeConfig config) throws GeneralSecurityException, IOException {
        this.webClient = webClient;
        this.config = config;
        youtubeService = new YouTubeFactory2().build(config.getApplicationName(), config.getClientSecretsFile());
    }

    /** Usage cost 300 credits */
    @Override
    public List<YTVideoData> findVideoData() {
        var videoDataList = new VideoListQuery(youtubeService, config.getChanelId()).execute();
        videoDataList.sort(Comparator.comparing(YTVideoData::getPublicationDate).reversed());
        return videoDataList;
    }

    @Override
    public Video findScheduledVideoStatus(String idVideoYT) {
        return new VideoStatusById2Query(youtubeService, idVideoYT).execute();
    }

}