package ch.mno.wc3.services.facebook.data;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
//@JsonIgnoreProperties(ignoreUnknown = true)
public class FBVideosData {

    private List<Datum> data = new ArrayList<>();

    public void addAll(FBVideosData other) {
        data.addAll(other.data);
    }

    public void dump() {
        getData().stream()
                .filter(d -> d.getDescription() != null)
                .forEach(d -> {
                    log.debug("Video {}", d.getId());
                    log.debug(" - {}", d.getDescription());
                    log.debug(" - {}", d.getUpdatedTime());
                    if (d.getAdditionalProperties() != null && !"{}".equals(d.getAdditionalProperties().toString().strip())) {
                        log.debug(" - {}", d.getAdditionalProperties());
                    }
                    log.debug("\n");
                });
    }

    public void mergeWith(FBVideosData other) {
        var myIds = getData().stream().map(Datum::getId).collect(Collectors.toSet());

        other.getData().stream()
                .filter(d -> !myIds.contains(d.getId()))
                .forEach(d -> data.add(d));
    }

    public record Phase(
            String status,

            @JsonAlias("publish_status")
            String publishStatus,

            @JsonAlias("publish_time")
            String publishTime
    ) {}


    public record Status(
            @JsonAlias("publishing_phase")
            Phase publishingPhase
    ) {}


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Datum {

        private String description;

        @JsonAlias("updated_time")
        private String updatedTime;

        private Status status;

        private String id;

        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<>();


        @Override
        public String toString() {
            return "Datum{" +
                    "description='" + description + '\'' +
                    ", updatedTime='" + updatedTime + '\'' +
                    ", id='" + id + '\'' +
                    ", additionalProperties=" + additionalProperties +
                    '}';
        }

        public Date getLastUpdatedTime() {
            var ts = LocalDateTime.parse(updatedTime.substring(0, 19)); // 2023-03-24T16:25:00+0000
            return Date.from(ts.atZone(ZoneId.systemDefault()).toInstant());
        }

        public Date getPublicationDate() {
            if (status!=null && status.publishingPhase()!=null && status.publishingPhase().publishTime!=null) {
                var publishTime = status.publishingPhase().publishTime;
                var ts = LocalDateTime.parse(publishTime.substring(0, 19)); // 2023-03-24T16:25:00+0000
                return Date.from(ts.atZone(ZoneId.systemDefault()).toInstant());
            }
            return null;
        }

        public boolean hasDescription() {
            return description!=null && !description.isBlank();
        }
    }


}