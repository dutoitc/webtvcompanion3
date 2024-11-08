package ch.mno.wc3.services.youtube.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "youtube")
@Getter
@Setter
@AllArgsConstructor // Needed by Builder
@NoArgsConstructor // Needed by ConfigurationProperties
@Builder
public class YoutubeConfig {

    private String chanelId;
    private String applicationName;
    private String clientSecretsFile;

}