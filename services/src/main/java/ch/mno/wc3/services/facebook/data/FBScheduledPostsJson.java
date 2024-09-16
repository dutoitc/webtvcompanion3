package ch.mno.wc3.services.facebook.data;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Slf4j
public class FBScheduledPostsJson {

    private List<Data> data = new ArrayList<>();
    private FBPagingJson paging;


    public void dump() {
        data.forEach(d->{
            log.debug("Post {}", d.getId());
            log.debug(" - {}", d.getCreated_time());
            log.debug(" - {}", d.getMessage());
        });
    }

    @Setter
    @Getter
    public static class Data {
        private String created_time;
        private String message;
        private String id;
    }

}