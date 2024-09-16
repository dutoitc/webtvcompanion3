package ch.mno.wc3.services.facebook.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
//@JsonIgnoreProperties(ignoreUnknown = true)
public class FBVideosJson extends FBVideosData {

    private FBPagingJson paging;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void dump() {
        getData().stream()
                .filter(d -> !(d.getDescription() == null))
                .forEach(d -> {
                    log.debug("Video {}", d.getId());
                    log.debug(" - {}", d.getDescription());
                    log.debug(" - {}", d.getUpdatedTime());
                    if (d.getAdditionalProperties() != null && !"{}".equals(d.getAdditionalProperties().toString().strip())) {
                        log.debug(" - {}", d.getAdditionalProperties());
                    }
                    log.debug("\n");
                });
        if (getAdditionalProperties() != null && !"{}".equals(getAdditionalProperties().toString().strip())) {
            log.debug("add: {}", getAdditionalProperties());
        }
        log.debug("Next page: {}", getPaging().getNext());
    }


}