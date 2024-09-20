package ch.mno.wc3.services.wordpress;

import ch.mno.wc3.services.wordpress.data.WPPostsRequest;
import ch.mno.wc3.services.wordpress.data.WPListResult;
import ch.mno.wc3.services.wordpress.data.WPVideosData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WordpressService {


    public WPVideosData listPosts(String baseUrl) {
        WPPostsRequest request = new WPPostsRequest(baseUrl);
        WPListResult result = request.execute();
        return result.getVideosData();
    }

}