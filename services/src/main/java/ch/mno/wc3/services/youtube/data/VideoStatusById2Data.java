package ch.mno.wc3.services.youtube.data;

import com.google.api.client.util.DateTime;
import lombok.Data;

@Data
public class VideoStatusById2Data {

    private String uploadStatus;
    private String privacyStatus;
    private DateTime publishAt;
    private boolean madeForKids;

}
