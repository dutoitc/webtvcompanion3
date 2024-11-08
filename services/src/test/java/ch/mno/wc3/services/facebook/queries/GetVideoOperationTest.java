package ch.mno.wc3.services.facebook.queries;

import ch.mno.wc3.services.facebook.config.FacebookConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GetVideoOperationTest extends AbstractOperationTest {

    @Test
    void testExecute_returnsCorrectValue() {
        FacebookConfig config = new FacebookConfig();
        config.setAppId("appId");
        config.setTokenApp("tokenApp");
        config.setTokenPage("tokenPage");

        var op = new GetVideoOperation(buildWebClient(), config);
        op.setBaseURL4Test(getBaseURL());

        assertEquals(3, op.execute().getData().size());
    }

}