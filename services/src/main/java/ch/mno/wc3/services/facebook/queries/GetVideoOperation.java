package ch.mno.wc3.services.facebook.queries;

import ch.mno.wc3.services.ServiceException;
import ch.mno.wc3.services.facebook.config.FacebookConfig;
import ch.mno.wc3.services.facebook.data.FBVideosData;
import ch.mno.wc3.services.facebook.data.FBVideosJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public class GetVideoOperation extends AbstractOperation<FBVideosData> {

    private static final String PATH_TEMPLATE = "/{appId}/videos";

    private final FacebookConfig config;

    public GetVideoOperation(WebClient webClient, FacebookConfig config) {
        super(webClient);
        this.config = config;
    }

    @Override
    protected String buildUrl() {
        var builder = UriComponentsBuilder.fromUriString(baseURL)
                .path(PATH_TEMPLATE);
        BASIC_OPTIONS.forEach(builder::queryParam);
        return builder.buildAndExpand(config.getAppId()).encode().toUriString();
    }

    @Override
    public FBVideosData execute() {
        var data1 = findByToken(config.getTokenPage());
        var data2 = findByToken(config.getTokenApp());
        data1.mergeWith(data2);
        if (data1.getData().isEmpty()) {
            log.warn("No data found, check your token and api request");
        }
        return data1;
    }

    private FBVideosData findByToken(String token) {
        try {
            String url =  buildUrl() + "&access_token=" + token + "&fields=status{publishing_phase},description&debug=all";
            var vd = new FBVideosData();

            while (url != null) {
                var vd2 = download(url, FBVideosJson.class);

                vd2.getData().stream()
                        .filter(FBVideosData.Datum::hasDescription)
                        .forEach(d->vd.getData().add(d)); // Facebook can create video objects with same video but no description, just ignore them

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