package ch.mno.wc3.services.youtube.data;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class YTVideoSummary {

    private String videoId;
    private LocalDateTime publicationDate;
    private String title;
    private String description;

    public YTVideoSummary() {
        this.publicationDate = LocalDate.now().atTime(11, 0);
    }

    public static YTVideoSummary of(String videoId, String title, String description, LocalDateTime publicationDate) {
        var vd = new YTVideoSummary();
        vd.setVideoId(videoId);
        vd.setTitle(title);
        vd.setDescription(description);
        vd.setPublicationDate(publicationDate);
        return vd;
    }


    @Override
    public String toString() {
        return "VideoData{" +
                "videoId=" + videoId +
                ", publicationDate=" + publicationDate +
                ", title=" + title +
                ", text=" + description +
                '}';
    }

}
