package ch.mno.wc3.services.facebook.queries;

import ch.mno.wc3.services.config.TestConfig;
import ch.mno.wc3.services.youtube.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.reactive.function.client.WebClient;

/** Base class for all Operation tests */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {MockController.class, TestConfig.class})
public abstract class AbstractOperationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @MockBean
    private YoutubeService youtubeServiceImpl;

    protected WebClient buildWebClient() {
        return webClientBuilder.baseUrl("http://localhost:" + port).build();
    }

    String getBaseURL() {
        return "http://localhost:" + port + "/v15.0";
    }

}