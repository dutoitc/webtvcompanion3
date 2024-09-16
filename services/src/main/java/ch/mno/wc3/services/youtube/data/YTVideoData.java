package ch.mno.wc3.services.youtube.data;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class YTVideoData {

    private LocalDateTime publicationDate;
    private String title;
    private String text;
    private String tags;
    private String downloadLink;
    private String localFile;
    private Boolean madeForKids;
    private String privacyStatus;
    private String uploadStatus;
    private String id;
    private YTVideoStats statistics;

    public YTVideoData() {
        this.publicationDate = LocalDate.now().atTime(11, 0);
    }

    public static YTVideoData of(String title, String text, String tags, String downloadLink, String localFile, LocalDateTime publicationDate, Boolean madeForKids, String privacyStatus, String uploadStatus) {
        var vd = new YTVideoData();
        vd.setTitle(title);
        vd.setText(text);
        vd.setTags(tags);
        vd.setDownloadLink(downloadLink);
        vd.setLocalFile(localFile);
        vd.setPublicationDate(publicationDate);
        vd.setMadeForKids(madeForKids);
        vd.setPrivacyStatus(privacyStatus);
        vd.setUploadStatus(uploadStatus);
        return vd;
    }


    @Override
    public String toString() {
        return "VideoData{" +
                "publicationDate=" + publicationDate +
                ", title=" + title +
                ", text=" + text +
                ", tags=" + tags +
                ", downloadLink=" + downloadLink +
                ", localFile=" + localFile +
                ", madeForKids=" + madeForKids +
                ", privacyStatus='" + privacyStatus + '\'' +
                ", uploadStatus='" + uploadStatus + '\'' +
                '}';
    }

}
