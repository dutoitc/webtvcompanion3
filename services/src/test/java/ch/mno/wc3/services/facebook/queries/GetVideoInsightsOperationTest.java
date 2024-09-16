package ch.mno.wc3.services.facebook.queries;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GetVideoInsightsOperationTest extends AbstractOperationTest {

    @Test
    void testExecute_returnsCorrectValue() {
        var op = new GetVideoInsightsOperation(buildWebClient(), "pageAccessToken", "videoId");
        op.setBaseURL4Test(getBaseURL());

        LinkedHashMap<String, String> data = op.execute();
        assertEquals("1357", data.get("total_video_views"));
        assertEquals("3888", data.get("total_video_impressions_viral"));
    }

}