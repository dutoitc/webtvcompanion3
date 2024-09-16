package ch.mno.wc3.services.facebook.data;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Slf4j
public class FBPagePost {

    private List<Data> data = new ArrayList<>();
    private FBPagingJson paging;


    public void add(FBPagePost posts) {
        data.addAll(posts.data);
        paging = null;
    }

    public void dump() {
        data.forEach(d -> {
            log.debug("Post {}", d.getId());
            log.debug(" - {}", d.getCreated_time());
            log.debug(" - {}", d.getMessage());
            if (d.getStory() != null) {
                log.debug(" - {}\n", d.getStory());
            }
        });
    }


    @Setter
    @Getter
    public static class Data {
        private String created_time;
        private String message;
        private String story;
        private String id;
        private int scheduled_publish_time;

        public LocalDateTime getScheduled_publish_timeAsObject() {
            return LocalDateTime.ofEpochSecond(scheduled_publish_time, 0, ZoneId.of("Europe/Zurich").getRules().getOffset(LocalDateTime.now()));// TOFIX
        }

    }

}