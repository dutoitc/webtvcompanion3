package ch.mno.wc3.services.instagram.operations;

import ch.mno.wc3.services.ServiceException;
import ch.mno.wc3.services.instagram.InstagramServiceImpl;
import ch.mno.wc3.services.instagram.data.IGVideosData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.net.URL;

@Slf4j
public class GetMediaOperation extends AbstractOperation<IGVideosData> {

    private String instagramId;
    private String tokenAccess;

    public GetMediaOperation(InstagramServiceImpl service, String instagramId, String tokenAccess) {
        super(service);
        this.instagramId = instagramId;
        this.tokenAccess = tokenAccess;
    }

    @Override
    public IGVideosData execute() {
        try {
            //17841451410581260/media?fields=id,media_type,media_url,owner,timestamp,caption,like_count,media_product_type,permalink,thumbnail_url,insights.metric(impressions,video_views)
            String url = "https://graph.facebook.com/v15.0/" + instagramId + "/media?access_token=" + tokenAccess + "&fields=id,media_type,media_url,owner,timestamp,caption,like_count,media_product_type,permalink,thumbnail_url,insights.metric(impressions,video_views,likes,plays)";
            var vd = new IGVideosData();

            ObjectMapper mapper = new ObjectMapper();
            while (url != null) {
                log.info("Calling {}", url);
                String json = IOUtils.toString(new URL(url), "UTF-8");
                var vd2 = mapper.readValue(json, IGVideosData.class);
                vd.getData().addAll(vd2.getData());

                // Next
                if (vd2.getPaging() != null && vd2.getPaging().getNext() != null) {
                    url = vd2.getPaging().getNext();
                } else {
                    url = null;
                }
            }

            return vd;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}