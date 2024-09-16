package ch.mno.wc3.services.facebook.queries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetVideoViewsOperationTest  extends AbstractOperationTest {


    @Test
    void testExecute_returnsCorrectValue() {
        var op = new GetVideoViewsOperation(buildWebClient(), "fakeAccessToken", "video123");
        op.setBaseURL4Test(getBaseURL());
        assertEquals(1357, op.execute());
    }

}
