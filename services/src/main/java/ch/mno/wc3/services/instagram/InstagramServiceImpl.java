package ch.mno.wc3.services.instagram;

import ch.mno.wc3.services.instagram.config.InstagramConfig;
import ch.mno.wc3.services.instagram.data.IGVideosData;
import ch.mno.wc3.services.instagram.operations.GetMediaOperation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class InstagramServiceImpl  {

    private InstagramConfig config;

    public IGVideosData getMedia() {
        return new GetMediaOperation(this, config.getInstagramId(), config.getAccessToken()).execute();
    }

}