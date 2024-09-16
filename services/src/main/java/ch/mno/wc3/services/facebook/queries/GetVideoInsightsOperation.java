package ch.mno.wc3.services.facebook.queries;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;

public class GetVideoInsightsOperation extends AbstractOperation<LinkedHashMap<String, String>> {

    private static final String PATH_TEMPLATE = "/{videoId}/video_insights";

    private final String videoId;
    private final String pageAccessToken;

    public GetVideoInsightsOperation(WebClient webClient, String pageAccessToken, String videoId) {
        super(webClient);
        this.pageAccessToken = pageAccessToken;
        this.videoId = videoId;
    }

    @Override
    protected String buildUrl() {
        var builder = UriComponentsBuilder.fromUriString(baseURL)
                .path(PATH_TEMPLATE)
                .queryParam("fields", "name,values")
                .queryParam("access_token", pageAccessToken);
        BASIC_OPTIONS.forEach(builder::queryParam);
        return builder.buildAndExpand(videoId).encode().toUriString();
    }

    @Override
    public LinkedHashMap<String, String> execute() {
        String json = download(buildUrl());
        return extractValues(json);
    }

    static LinkedHashMap<String, String> extractValues(String json) {
        LinkedHashMap<String, String> values = new LinkedHashMap<>();
        JSONObject js = new JSONObject(json);
        if (!js.has("data")) {
            System.err.println("No data found");
            return values;
        }
        JSONArray jsData = (JSONArray) js.get("data");
        for (int i = 0; i < jsData.length(); i++) {
            var jsData2 = jsData.getJSONObject(i);
            var name = jsData2.getString("name");
            var jsValues = jsData2.getJSONArray("values");
            var value = "?";
            if (name.equals("total_video_reactions_by_type_total")) {
                JSONObject jsonObject = jsValues.getJSONObject(0);
                if (jsonObject.has("value")) {
                    JSONObject vObject = jsonObject.getJSONObject("value");
                    int nbReactions = 0;
                    for (var k : vObject.keySet()) {
                        nbReactions += vObject.getInt(k);
                    }
                    value = "" + nbReactions;
                }
            } else if (jsValues.length() == 1) {
                JSONObject jsonObject = jsValues.getJSONObject(0);
                if (jsonObject.has("value")) {
                    value = jsonObject.get("value").toString();
                }
            }
            values.put(name, value);
        }
        return values;
    }
}
