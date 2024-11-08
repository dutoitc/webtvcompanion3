package ch.mno.wc3.services.youtube;

import ch.mno.wc3.services.youtube.queries.VideoListQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;

public class VideoListQueryTest {

    @Test
    @Disabled
        // Not working, need to find good api mock
    void testX() throws IOException {
        // Mock return
        InputStream inputStream = getClass().getResourceAsStream("/youtube-videolist.json");
        ObjectMapper objectMapper = new ObjectMapper();
        SearchListResponse result = objectMapper.readValue(inputStream, SearchListResponse.class);

        // Mock api call
        var search = Mockito.mock(YouTube.Search.class);
        Mockito.when(search.list(Mockito.anyString())).thenReturn(null);
        var service = Mockito.mock(YouTube.class);
        Mockito.when(service.search()).thenReturn(search);

        // Test
        var query = new VideoListQuery(service, "dummy");
        var ret = query.execute();
    }

}
