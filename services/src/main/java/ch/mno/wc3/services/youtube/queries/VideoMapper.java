package ch.mno.wc3.services.youtube.queries;

import ch.mno.wc3.services.youtube.data.YTVideoData;
import ch.mno.wc3.services.youtube.data.YTVideoStats;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class VideoMapper {

    private VideoMapper() {
        // No usage
    }

    public static YTVideoData map(Video pVideo) {
        YTVideoData vd = new YTVideoData();
        vd.setId(pVideo.getId());
        mapVideoStatus(vd, pVideo);
        mapVideoSnippet(vd, pVideo);
        mapVideoStatistics(vd, pVideo);
        return vd;
    }

    private static void mapVideoStatistics(YTVideoData vd, Video pVideo) {
        var stats = pVideo.getStatistics();
        if (stats!=null) {
            var out = new YTVideoStats();
            if (stats.getCommentCount()!=null) out.setCommentCount(stats.getCommentCount().intValue());
            if (stats.getDislikeCount()!=null) out.setDislikeCount(stats.getDislikeCount().intValue());
            if (stats.getFavoriteCount()!=null) out.setFavoriteCount(stats.getFavoriteCount().intValue());
            if (stats.getLikeCount()!=null) out.setLikeCount(stats.getLikeCount().intValue());
            if (stats.getViewCount()!=null) out.setViewCount(stats.getViewCount().intValue());
            vd.setStatistics(out);
        }
    }

    private static void mapVideoSnippet(YTVideoData vd, Video pVideo) {
        VideoSnippet snippet = pVideo.getSnippet();
        if (snippet != null) {
            vd.setTitle(snippet.getTitle());
            vd.setPublicationDate(LocalDateTime.ofEpochSecond(snippet.getPublishedAt().getValue() / 1000, 0, ZoneOffset.UTC));
            if (snippet.getTags() == null) {
                vd.setTags("");
            } else {
                vd.setTags(snippet.getTags().toString());
            }
            vd.setText(snippet.getDescription());
        }
    }


    private static void mapVideoStatus(YTVideoData vd, Video item0) {
        // status fields not mapped: "embeddable":true, "license":"youtube",  "publicStatsViewable":true, "selfDeclaredMadeForKids":false
        VideoStatus status = item0.getStatus();
        if (status != null) {
            vd.setMadeForKids(status.getMadeForKids());
            vd.setPrivacyStatus(status.getPrivacyStatus());
            vd.setUploadStatus(status.getUploadStatus());
        }
        // duration PT11M39S
    }

}