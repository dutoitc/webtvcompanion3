package ch.mno.wc3.services.facebook.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "facebook")
@Getter
@Builder
@AllArgsConstructor // Needed by Builder
@NoArgsConstructor // Needed by ConfigurationProperties
public class FacebookConfig {

    private String appId;
    private String tokenApp;
    private String tokenPage;

}