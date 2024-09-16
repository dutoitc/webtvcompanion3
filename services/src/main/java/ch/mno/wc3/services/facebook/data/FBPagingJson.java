package ch.mno.wc3.services.facebook.data;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.HashMap;
import java.util.Map;

@Data
@Jacksonized
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FBPagingJson {

    private FBCursorsJson cursors;
    private String next;
    private String previous;
    private Map<String, Object> additionalProperties = new HashMap<>();

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}